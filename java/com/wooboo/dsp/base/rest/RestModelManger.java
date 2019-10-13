package com.wooboo.dsp.base.rest;

import com.wooboo.dsp.base.enums.NijiadehuaEnums;


/**
 * CREATE RESTMODEL BY ENUM
 * 
 * @author Administrator
 * 
 */
public class RestModelManger {
	/**
	 * 
	 * @param bllEnum
	 *            信息提示枚举
	 * @param data
	 * @return
	 */
	public static RestModel getRestModel(NijiadehuaEnums bllEnum, Object data) {
		return RestModel
				.getResModel(bllEnum.getCode(), bllEnum.getDesc(), data);
	}

	/**
	 * 
	 * @param code
	 * @param desc
	 * @param data
	 * @return
	 */
	public static RestModel getRestModel(NijiadehuaEnums bllEnum, String desc,
			Object data) {
		RestModel rm = new RestModel();
		rm.setCode(bllEnum.getCode());
		rm.setErrorDescription(desc);
		rm.setDataObject(data);
		return rm;
	}
	


}
