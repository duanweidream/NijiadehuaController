package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.base.enums.Status.BusinessCode;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.util.SpringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;


public class SysLoggerTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long oid = null;
	private String code = null;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
		SystemService systemService = SpringUtil.getBean("systemService", SystemService.class);
		List<SysLogger> list = systemService.querySysLogger(oid, code, 20);
		pageContext.getOut().write(html(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
	public String html(List<SysLogger> list){
		StringBuffer sbf = new StringBuffer("<div class=\"row\"><div class=\"col-md-12\">");
		sbf.append("<ul class=\"list-group\">");
		
		if(BusinessCode.ideaOpenOff == BusinessCode.fromValue(code)){
			sbf.append("<h3 class=\"caption\">素材上下线记录</h3>");
		}else{
			sbf.append("<h3 class=\"caption\">上线日志</h3>");
		}
		for(SysLogger logger:list){
			sbf.append("<li class=\"list-group-item\">");
			sbf.append(DateUtil.getFormatDate(logger.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
			sbf.append(" ["+logger.getOperateName()+"]  :"+logger.getUserName()+""+logger.getOperateDesc()+"</li>");
		}
		sbf.append("</ul>");
		sbf.append("</div></div>");
		return sbf.toString();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	
	
	

	}
