package com.wooboo.dsp.base.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.wooboo.dsp.base.log.Logger;

/**
 * 
 * @author Administrator
 * 
 */
public class ResponsePrint {
	private static Logger logger = Logger.getLogger(ResponsePrint.class);

	/**
	 * RESPONSE 输出 STR
	 * 
	 * @param response
	 * @param msg
	 * @throws IOException
	 */
	public static void outStr(HttpServletResponse response, String msg) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/json;charset=utf-8");
			response.setContentLength(msg.getBytes("utf-8").length);
			response.getWriter().append(msg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			logger.logError(e.getMessage(), e);
		} catch (Exception e) {
			logger.logError(e.getMessage(), e);
		}

	}
}
