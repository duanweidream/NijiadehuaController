package com.wooboo.dsp.xss;

import java.beans.PropertyEditorSupport;
import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

/**
 * 
 * 重载spring的属性编辑器，在conteoller中bind使用
 * 
 * @duanwei
 * 
 */
public class StringEscapeEditor extends PropertyEditorSupport {

	private boolean escapeHTML;// 编码HTML
	private boolean escapeJavaScript;// 编码javascript

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);
				System.out.println("value:" + value);
			}
			if (escapeJavaScript) {
				value = JavaScriptUtils.javaScriptEscape(value);
				System.out.println("value:" + value);
				
			}
			setValue(value);
		}
	}

	public String getAsText(String text) throws IllegalArgumentException {
		if (text == null) {
			return "";
		} else {
			String value = text;
			if (true) {
				value = HtmlUtils.htmlEscape(value);
				
				System.out.println("value:" + value);
			}

			return value;
		}
	}
	
}
