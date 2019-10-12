package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.base.enums.Status;
import com.wooboo.dsp.model.SysDepartMent;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;


public class SysUserNameTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long uid;
	
	private SystemService systemService;
	
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			systemService = SpringUtil.getBean("systemService",SystemService.class);
			SysUser sysUser = systemService.getObject(uid, SysUser.class);
			if(sysUser != null){
				pageContext.getOut().write(sysUser.getNickName() + "("+sysUser.getUserName()+")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	
	
	

	
	

	
	
}
