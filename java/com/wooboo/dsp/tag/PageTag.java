package com.wooboo.dsp.tag;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.wooboo.dsp.system.util.Page.Page;


/**
 * @author Administrator
 *
 */
public class PageTag extends TagSupport  {

	private String colnum = null;

	

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		Page page = (Page)pageContext.getRequest().getAttribute("page_list");
		try {
			if (page != null) {
				String html = "";
				html = getHTML(page);
				pageContext.getOut().write(html);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	public String getHTML(Page page) throws  IOException {
		
		
		StringBuffer sb = new StringBuffer(" <div class=\"row\">");
		
		if(page!=null){
			
			int i = page.getPageSize();
			int t = page.getTotalPages();
			int c = page.getCurrentPage();
			int b = getbegin(c, t);
			int e = getEnd(c, t);
			
            sb.append("<div class=\"col-md-5 col-sm-12\">");
            sb.append("<div class=\"dataTables_info\" id=\"sample_1_info\" role=\"status\" aria-live=\"polite\" style=\"font-size:10px;\">显示  第"+page.getCurrentPage()+"页 共"+page.getTotalPages()+"页</div>");
			sb.append(" </div>");
			
			sb.append("<div class=\"col-md-7 col-sm-12\">");
			sb.append("<div class=\"dataTables_paginate paging_bootstrap_number\" id=\"sample_1_paginate\">");
			sb.append("<ul class=\"pagination\" style=\"visibility: visible;\">");
			
			if(page.isFirst()){
				sb.append("<li class=\"prev disabled\"><a href=\"?\" title=\"Prev\"><i class=\"fa fa-angle-left\"></i></a></li>");
			}else{
				sb.append("<li><a href=\"?"+1+"\" >首页</a></li>");
				sb.append("<li class=\"prev\"><a href=\"?"+page.getQuery("prev")+"\" title=\"Prev\"><i class=\"fa fa-angle-left\"></i></a></li>");
			}
			for (; b <= e; b++) {
				if(c==b){
					sb.append("<li class=\"active\"><a href=\"#\"  >"+b+"</a></li>");
				}else{
					sb.append("<li><a href=\"?"+page.getQueryPageSize(b)+"\" >"+b+"</a></li>");
				}
			
			}
			
			if(page.isLast()){
				sb.append("<li class=\"next disabled\"><a href=\"#\" title=\"Next\"><i class=\"fa fa-angle-right\"></i></a></li>");
			}else{
				sb.append("<li class=\"next\"><a href=\"?"+page.getQuery("next")+"\" title=\"Next\"><i class=\"fa fa-angle-right\"></i></a></li>");
				sb.append("<li><a href=\"?"+ page.getQuery("last")+"\" >尾页</a></li>");
			}
            
            sb.append("</ul></div></div>");
		}
			
			/**
			int i = page.getPageSize();
			int t = page.getTotalPages();
			int c = page.getCurrentPage();
			int b = getbegin(c, t);
			int e = getEnd(c, t);
			sb.append("<ul>");
			
			sb.append("<a href='?"+page.getQuery("first")+"' class=\"button_01\">首页</a>&nbsp;");
			if(page.isFirst()){
				sb.append("<li class=\"disabled\"><a  href=\"#\">上一页</a></li>");
			}else{
				sb.append("<li><a href='?"+page.getQuery("prev")+"'>上一页</a></li>");
			}
			
			for (; b <= e; b++) {
				sb.append("<li "+ (c == b ? "class=\"disabled\"": "")+"class=\"disabled\"><a   href=\"#\">"+b+"</a></li>");
			}
			sb.append("<li><a href=\"#\">下一页</a></li>");
			sb.append("</ul>");
		}
		sb.append("</div>");
		*/
		return sb.toString();
	}

	public int getbegin(int c, int t) {
		int b = 1;
		if (c - 2 > 1)
			b = c - 2;
		if (t - (c + 2) < 0) {
			b = b + (t - (c + 2));
		}
		if (b < 1) {
			b = 1;
		}
		return b;
	}

	public int getEnd(int c, int t) {
		int e = t;
		if (e > 5) {
			if (c + 2 < t)
				e = c + 2;
			if (c - 2 <= 0)
				e = e - (c - 2) + 1;
		}
		return e;
	}

	public String getColnum() {
		return colnum;
	}

	public void setColnum(String colnum) {
		this.colnum = colnum;
	}



	


}
