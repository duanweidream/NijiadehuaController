package com.wooboo.dsp.base.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.wooboo.dsp.base.enums.NijiadehuaEnums;
import com.wooboo.dsp.base.json.JascksonUtil;
import com.wooboo.dsp.base.log.Logger;

/**
 * rest model util
 * 
 * @author Administrator
 * 
 */
public class RestModelToJson {

	static Logger logger = Logger.getLogger(RestModelToJson.class);

	/**
	 * REST MODEL TO JSON STR
	 * 
	 * @param model
	 * @return
	 */
	public static String getRestJosn(RestModel model) {
		String json = "";
		long code = NijiadehuaEnums.SERVICE_EXCEPTION.getCode();
		String desc = NijiadehuaEnums.SERVICE_EXCEPTION.getDesc();
		try {
			if (model == null) {
				json = RestModel.getRestModel(code, desc).toString();
			} else {
				json = JascksonUtil.objectToJackson(model, RestModel.class);
			}
		} catch (JsonGenerationException e) {
			logger.logError(e.getMessage(), e);
			code = NijiadehuaEnums.SERVICE_EXCEPTION.getCode();
			desc = e.getMessage();
		} catch (JsonMappingException e) {
			logger.logError(e.getMessage());
			code = NijiadehuaEnums.SERVICE_EXCEPTION.getCode();
			desc = e.getMessage();
		} catch (IOException e) {
			logger.logError(e.getMessage(), e);
			code = NijiadehuaEnums.SERVICE_EXCEPTION.getCode();
			desc = e.getMessage();
		} finally {
			if (json == null || "".equals(json)) {
				json = RestModel.getRestModel(code, desc).toString();
			}
			logger.debug("RET JSON={}", json);
		}
		return json;
	}
	
	
	
}
