package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.base.enums.Status;
import com.wooboo.dsp.model.SysDepartMent;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;


public class StatusName extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private String type;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
		pageContext.getOut().write(Status.toName(value, type));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	
	
}
