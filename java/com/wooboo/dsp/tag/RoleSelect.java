package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.model.RoleInfo;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;

import java.util.List;

import javax.servlet.jsp.JspException;


public class RoleSelect extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer roleId;
	
	private SystemService systemService;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
		pageContext.getOut().write(html());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}
	public String html(){
		systemService = SpringUtil.getBean("systemService",SystemService.class);
		StringBuffer sbuf = new StringBuffer();
		
		List<RoleInfo> list =   systemService.queryRoleInfo();
		for(RoleInfo role:list){
			sbuf.append("<option value=\""+role.getRoleId()+"\"  "+(StringUtil.equals(role.getRoleId(),roleId)?"selected":"")+"   >"+"--"+role.getRoleName()+"   </option>");

		}
		return sbuf.toString();
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	


	
	
	
}
