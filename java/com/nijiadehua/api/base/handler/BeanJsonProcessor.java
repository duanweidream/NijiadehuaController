/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nijiadehua.api.base.handler;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;




/**
 * <p>
 * <code>BeanProcessor</code> matches column names to bean property names and
 * converts <code>ResultSet</code> columns into objects for those bean
 * properties. Subclasses should override the methods in the processing chain to
 * customize behavior.
 * </p>
 * 
 * <p>
 * This class is thread-safe.
 * </p>
 * 
 * @see BasicRowProcessor
 * 
 * @since DbUtils 1.1
 */
public class BeanJsonProcessor implements JsonRowProcessor {

	private static final Map<Class<?>, Object> primitiveDefaults = new HashMap<Class<?>, Object>();

	static {
		primitiveDefaults.put(Integer.TYPE, Integer.valueOf(0));
		primitiveDefaults.put(Short.TYPE, Short.valueOf((short) 0));
		primitiveDefaults.put(Byte.TYPE, Byte.valueOf((byte) 0));
		primitiveDefaults.put(Float.TYPE, Float.valueOf(0f));
		primitiveDefaults.put(Double.TYPE, Double.valueOf(0d));
		primitiveDefaults.put(Long.TYPE, Long.valueOf(0L));
		primitiveDefaults.put(Boolean.TYPE, Boolean.FALSE);
		primitiveDefaults.put(Character.TYPE, Character.valueOf((char) 0));
	}

	public Object[] toArray(JSONArray rs) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T> T toBean(JSONArray rs, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public <T> T toBean(JSONObject o, Class<T> type) {
		PropertyDescriptor[] props = this.propertyDescriptors(type);
		return createBean(o, type, props);
	}

	public <T> List<T> toBeanList(JSONArray rs, Class<T> type) {
		List<T> results = new ArrayList<T>();
		PropertyDescriptor[] props = this.propertyDescriptors(type);
		for (int i = 0; i < rs.size(); i++) {
			results.add(this.createBean(rs, i, type, props));
		}
		return results;
	}

	
	public Map<String, Object> toMap(JSONArray rs) {
		// TODO Auto-generated method stub
		return null;
	}

	private PropertyDescriptor[] propertyDescriptors(Class<?> c) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(c);
		} catch (IntrospectionException e) {
		}
		return beanInfo.getPropertyDescriptors();
	}

	private <T> T createBean(JSONArray rs, int index, Class<T> type,
			PropertyDescriptor[] props) {
		T bean = this.newInstance(type);
		for (int i = 0; i < props.length; i++) {			
			JSONObject o = rs.getJSONObject(index);
			PropertyDescriptor prop = props[i];
			
			if (o.has(prop.getName())) {
				Class<?> propType = prop.getPropertyType();
				Object value = this.processColumn(o, prop.getName(), propType);
				if (propType != null && value == null && propType.isPrimitive()) {
					value = primitiveDefaults.get(propType);
				}
				this.callSetter(bean, prop, value);
			}
		}
		return bean;
	}

	private <T> T createBean(JSONObject o, Class<T> type,PropertyDescriptor[] props) {
		T bean = this.newInstance(type);
		for (int i = 0; i < props.length; i++) {
			PropertyDescriptor prop = props[i];
			if (o.has(prop.getName())) {
				Class<?> propType = prop.getPropertyType();
				Object value = this.processColumn(o, prop.getName(), propType);
				if (propType != null && value == null && propType.isPrimitive()) {
					value = primitiveDefaults.get(propType);
				}
				this.callSetter(bean, prop, value);
			}
		}
		return bean;
	}
	
	
	protected <T> T newInstance(Class<T> c) {
		try {
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	protected Object processColumn(JSONObject rs, String name, Class<?> propType) {
		Object object = rs.get(name);
		//System.out.println(propType+"=========="+name+" "+object);
		if (propType.equals(String.class)) {
			return object.toString();
		} else if (propType.equals(Integer.TYPE)|| propType.equals(Integer.class)) {
			return parseInteger(object,0);
			//return Integer.valueOf(object.toString());
		} else if (propType.equals(Boolean.TYPE)|| propType.equals(Boolean.class)) {
			return Boolean.valueOf(rs.getBoolean(name));
		} else if (propType.equals(Long.TYPE) || propType.equals(Long.class)) {
			return Long.valueOf(rs.getLong(name));
		} else if (propType.equals(Double.TYPE)|| propType.equals(Double.class)) {
			return Double.valueOf(rs.getDouble(name));
		} else if (propType.equals(Float.TYPE) || propType.equals(Float.class)) {
			return Float.valueOf(object.toString());
		} else if (propType.equals(Short.TYPE) || propType.equals(Short.class)) {
			return Short.valueOf(object.toString());
		} else if (propType.equals(Byte.TYPE) || propType.equals(Byte.class)) {
			return Byte.valueOf(object.toString());
		} else if (propType.equals(Timestamp.class)) {
			return "";
		} else if(propType.equals("class java.util.Date")) {
			return object;
		} else {
			return object;
		}
		
		
	}
	
	
    private Integer parseInteger(Object o,Integer def){
    	if(null!=o){
    		try{
    			return Integer.valueOf(o.toString());
    		}catch(Exception e){
    			return def;
    		}
    	}
    	return def;
    }
	private void callSetter(Object target, PropertyDescriptor prop, Object value) {
		Method setter = prop.getWriteMethod();
		if (setter == null) {
			return;
		}
		Class<?>[] params = setter.getParameterTypes();
		try {
			// convert types for some popular ones
		
			if (value != null) {
				if("java.util.Date".equals(prop.getPropertyType().getName()) && value instanceof Long){
					long timestamp = Long.parseLong(value.toString());
					//Date d = (Date) DateUtil.getDate(timestamp);
					value = new Date(timestamp);
				}
				
				
				
				if (value instanceof java.util.Date) {
					if (params[0].getName().equals("java.sql.Date")) {
						value = new java.sql.Date(((java.util.Date) value).getTime());
					} else if (params[0].getName().equals("java.sql.Time")) {
						value = new java.sql.Time(((java.util.Date) value).getTime());
					} else if (params[0].getName().equals("java.sql.Timestamp")) {
						value = new java.sql.Timestamp(((java.util.Date) value).getTime());
					}
				}
			}

			// Don't call setter if the value object isn't the right type
			if (this.isCompatibleType(value, params[0])) {
				setter.invoke(target, new Object[] { value });
			} else {
			
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	

	private boolean isCompatibleType(Object value, Class<?> type) {
		// Do object check first, then primitives
		if (value == null || type.isInstance(value)) {
			return true;

		} else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
			return true;

		} else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
			return true;

		} else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
			return true;

		} else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
			return true;

		} else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
			return true;

		} else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
			return true;

		} else if (type.equals(Character.TYPE)
				&& Character.class.isInstance(value)) {
			return true;

		} else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
			return true;

		}
		return false;

	}

	
	public <T> JSONArray toJSONArray(List<T> list) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public <T> JSONObject toJSONObject(Object o, Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
