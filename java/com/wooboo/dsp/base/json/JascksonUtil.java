package com.wooboo.dsp.base.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.wooboo.dsp.base.log.Logger;

public class JascksonUtil {
	private static Logger logger = Logger.getLogger(JascksonUtil.class);

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
		object = JascksonUtil.jsongToObject(json, Object.class);
		logger.debug("object{}", object);

		return object;
	}


}