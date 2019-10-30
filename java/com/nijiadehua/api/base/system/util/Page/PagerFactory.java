package com.nijiadehua.api.base.system.util.Page;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PagerFactory {
    public Page getPager(String currentPage,String pagerMethod,int totalRows) {
        // 定义pager对象，用于传到页面
    	PageImpl pager = new PageImpl(totalRows);
        // 如果当前页号为空，表示为首次查询该页
        // 如果不为空，则刷新pager对象，输入当前页号等信息
        if (currentPage != null) {
            pager.refresh(Integer.parseInt(currentPage));
        }
        // 获取当前执行的方法，首页，前一页，后一页，尾页。
        if (pagerMethod != null) {
            if (pagerMethod.equals("first")) {
                pager.first();
            } else if (pagerMethod.equals("previous")) {
                pager.previous();
            } else if (pagerMethod.equals("next")) {
                pager.next();
            } else if (pagerMethod.equals("last")) {
                pager.last();
            }
        }
        return pager;
    }
    
    public static Page createPage(){
    	ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();//ServletActionContext.getRequest();
		int from = PageUtil.getInt(request.getParameter(ATTPage.FROM), 0);
		int pgSize = PageUtil.getInt(request.getParameter(ATTPage.PAGE_SIZE), ATTPage.DEFAULT_SIZE);
		int titSize = PageUtil.getInt(request.getParameter(ATTPage.TOTAL_SIZE), -1);
		PageImpl page = new PageImpl(toQueryString(request));
		page.setPageSize(pgSize);
		page.setStartRow(from);
		page.setTotalRows(titSize);
		if(!page.isNew()){ 
			if(pgSize>=titSize){
				page.setStartRow(0);
			}
		}
    	return page;
    }
    
	public static String toQueryString(HttpServletRequest request) {
		StringBuffer bf = new StringBuffer(100);
		String common = "";
		String name = "";
		String value = "";
		String[] temp=null;
		Enumeration <String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			name = PageUtil.dealNull(en.nextElement());
			temp=request.getParameterValues(name);
			if(temp.length<=1){
				value = PageUtil.dealNull(request.getParameter(name));
			}else{
				String t="";
				StringBuffer vBuffer=new StringBuffer();
				for(String s:temp){
					vBuffer.append(t+s);
					t=",";
				}
				value=vBuffer.toString();
			}
			
			
			
			if (name.equals(ATTPage.FROM)) {
				continue;
			}
			if (!PageUtil.isEmpty(value)) {
				bf.append(common);
				bf.append(name);
				bf.append("=");
				bf.append(PageUtil.encode(value, PageUtil.dealNull(request.getCharacterEncoding(), "utf-8")));
				common = "&";
			}
		}
		return bf.toString();
	}
}