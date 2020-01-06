package com.nijiadehua.api.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.nijiadehua.api.base.log.Logger;

/**
 * <p>
 * Title: DSP平台
 * </p>
 *
 * <p>
 * Description: DSP Platform
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2015.5.28
 * </p>
 *
 * <p>
 * Company: Wooboo
 * </p>
 *
 * 类名称：JsonUtils 类描述： 封装JSON对象转换 创建人：duanwei 创建时间�?015-5-28 下午5:13:21
 * 修改人：duanwei 修改时间�?015-5-28 下午5:13:21 修改备注�?
 * 
 * @author duanwei
 * 
 * @version 1.0.0
 */
public class JsonUtil {

	private static Logger logger = Logger.getLogger(JsonUtil.class);

	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static String objectToJackson(Object json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerSubtypes(cls);
		String reqJson = mapper.writeValueAsString(json);
		return reqJson;
	}

	/**
	 * 
	 * @param json
	 * @param cls
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jsongToObject(String json, Class cls)
			throws JsonGenerationException, JsonMappingException, IOException {
		Object obj = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		obj = mapper.readValue(json, cls);
		return obj;
	}

	public static Map<String, Object> toMap(String json) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Object> map = null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		map = mapper.readValue(json, Map.class);
		return map;
	}

	public static List<Map> toMapList(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Map.class);
		List<Map> lst = (List<Map>) mapper.readValue(json, javaType);
		return lst;
	}

	public static List<Map> toObjectList(String json, Class cls)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, cls);
		List lst = (List) mapper.readValue(json, javaType);
		return lst;
	}

	public static String toJson(Map<String, String> map) {
		Set<String> keys = map.keySet();
		String key = "";
		String value = "";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = it.next();
			value = map.get(key);
			jsonBuffer.append(key + ":" + value);
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}

	/**
	 * 
	 * @param json
	 * @param c
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	public static Object jsonToObject(String json) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = null;
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		object = JsonUtil.jsongToObject(json, Object.class);
		logger.debug("object{}", object);

		return object;
	}
}