package com.wooboo.dsp.tag;

import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.util.AESKit;
import com.wooboo.dsp.util.UtilFunction;

import javax.servlet.jsp.JspException;


public class AESKitTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private String encrypt;
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			if(!UtilFunction.isEmpty(value)){
				if("1".equals(encrypt)){
					pageContext.getOut().write(AESKit.Decrypt(value));
				}else{
					pageContext.getOut().write(value);
				}
				
			}else{
				pageContext.getOut().write("");
			}
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

	public String getEncrypt() {
		return encrypt;
	}

	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	
	
	
	
	
}
