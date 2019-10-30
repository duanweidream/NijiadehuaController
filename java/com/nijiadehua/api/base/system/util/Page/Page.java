package com.nijiadehua.api.base.system.util.Page;

import java.util.List;

import net.sf.json.JSONObject;

public interface Page {   
    public int getStartRow();
    public int getTotalPages();
    public int getTotalRows();
    public int getCurrentPage();
    public int getPageSize();
    public void setTotalRows(int totalRows);
    public String getQuery(String method);
    public String getQueryPageSize(Integer size);
    public String getSizeQuery(int size);
	public List getList();
	public void setList(List list);
	public boolean isNew();
	public Boolean isFirst();
	public Boolean isLast();
	public void setPageSize(int PageSize);
	public void setStartRow(int PageSize);
	public JSONObject toPageFooter();
}
