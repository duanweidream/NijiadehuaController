package com.nijiadehua.api.base.system.util.Page;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;


public class PageImpl implements Page {
    private int totalRows = -1; //总行数
    private int pageSize = 5; //每页显示的行数
    private int currentPage; //当前页号
    private int totalPages; //总页数
    private int startRow; //当前页在数据库中的起始行
	private String query = null; //查询串
	private List list = null;
	
    public PageImpl() {
    }
    
    public PageImpl(String strQuery) {
    	query = strQuery;
    }
    
    public PageImpl(int _totalRows) {
        totalRows = _totalRows;
        totalPages=totalRows/pageSize;
        int mod=totalRows%pageSize;
        if(mod>0){
            totalPages++;
        }
        currentPage = 1;
        startRow = 0;
    }
    
    public int getStartRow() {
        return startRow;
    }
    
    public int getTotalPages() {
    	if(totalPages==0){
    		totalPages = (int)Math.ceil(1.0 * totalRows / pageSize);   
    	}
        return totalPages;
    }
    
    public int getCurrentPage() {
    	if(currentPage==0){
    		currentPage = (int)Math.ceil(1.0 * startRow / pageSize);  
    	}
        return currentPage+1;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }
    
    public void setStartRow(int startRow) {
        this.startRow = startRow<0 ? 0 : startRow;
    }
    
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public int getTotalRows() {
        return totalRows;
    } 
    
    public String getQueryPageSize(Integer size) {
    	int from = getPageSize()*(size-1);
    	
    	if(from<0){
    		from = 0;
    	}
    	if(from>=totalRows){
    		from = getTotalPages()*pageSize-pageSize;
    	}
    	StringBuffer sb = new StringBuffer();
		sb.append("&");
 		sb.append(ATTPage.FROM);
		sb.append("=");
		sb.append(from);
		boolean empty = PageUtil.isEmpty(query);
		if(empty||!query.matches(".*"+ATTPage.TOTAL_SIZE+"\\s*=\\s*\\d*.*")){
			append(sb, ATTPage.TOTAL_SIZE, totalRows);
			if(!empty){
				append(sb, query);
			}
		}
		else{
			append(sb, query.replaceFirst(ATTPage.TOTAL_SIZE+"\\s*=\\s*\\d*", ATTPage.TOTAL_SIZE+"="+totalRows));
		}
		return sb.length()>0 ? sb.substring(1) : "";
	}
    
    
    
    public String getQuery(String method) {
    	int from = startRow;
    	if("next".equals(method)){
    		from = startRow+pageSize;
    	}else if("prev".equals(method)){
    		from = startRow-pageSize;
    	}else if("first".equals(method)){
    		from = 0;
    	}else if("last".equals(method)){
    		from = getTotalPages()*pageSize-pageSize;
    	}else if(method!=null){
    		Pattern pattern = Pattern.compile("\\d+");
    		Matcher matcher = pattern.matcher(method);
    		if (matcher.find()) {
    			int size = Integer.valueOf(matcher.group());
    			if(size>totalRows || startRow + size > totalRows){
    				from = 0;
    			}
    		}    		
    	}
    	if(from<0){
    		from = 0;
    	}
    	if(from>=totalRows){
    		from = getTotalPages()*pageSize-pageSize;
    	}
    	StringBuffer sb = new StringBuffer();
		sb.append("&");
 		sb.append(ATTPage.FROM);
		sb.append("=");
		sb.append(from);
		boolean empty = PageUtil.isEmpty(query);
		if(empty||!query.matches(".*"+ATTPage.TOTAL_SIZE+"\\s*=\\s*\\d*.*")){
			append(sb, ATTPage.TOTAL_SIZE, totalRows);
			if(!empty){
				append(sb, query);
			}
		}
		else{
			append(sb, query.replaceFirst(ATTPage.TOTAL_SIZE+"\\s*=\\s*\\d*", ATTPage.TOTAL_SIZE+"="+totalRows));
		}
		return sb.length()>0 ? sb.substring(1) : "";
	}
    
    
    private void append(StringBuffer sb, Object str){
    	if(sb.length()>0){
    		sb.append("&");	
    	}
    	sb.append(str);  
    }
    
    private void append(StringBuffer sb, String key, Object value){
    	if(sb.length()>0){
    		sb.append("&");	
    	}
		sb.append(key);
		sb.append("=");
		sb.append(value);    	
    }
    
    // 黄轲
    private void appendWap(StringBuffer sb, String key, Object value) {
    	if(sb.length()>0){
    		sb.append("/");	
    	}
		sb.append(value);
    }
    
    private void append(StringBuffer sb, String str){
    	if(sb.length()>0){
    		sb.append("&");	
    	}
		sb.append(str);  	
    }
    
    // 黄轲
    private void appendWap(StringBuffer sb, String str){
    	if(sb.length()>0){
    		sb.append("/");	
    	}
		sb.append(str); 
    }
    
    public String getSizeQuery(int size){
    	String query = getQuery(String.valueOf(size));
    	StringBuffer sb = new StringBuffer();
		if(PageUtil.isEmpty(query)){
			append(sb, ATTPage.PAGE_SIZE, size);
		}else{
			if(query.matches(".*"+ATTPage.PAGE_SIZE+"\\s*=\\s*\\d*.*")){
				append(sb, query.replaceFirst(ATTPage.PAGE_SIZE+"\\s*=\\s*\\d*", ATTPage.PAGE_SIZE+"="+size));
			}else{
				append(sb, ATTPage.PAGE_SIZE, size);
				append(sb, query);
			}
		}
		return sb.toString();
    }
    
	public void setQuery(String query) {
		this.query = query;
	}
	
	public List getList() {
		return list;
	}
	
	public void setList(List list) {
		this.list = list;
	}

	public boolean isNew(){
		return totalRows == -1;
	}

	public void first() {
        currentPage = 1;
        startRow = 0;
    }
	
    public void previous() {
        if (currentPage == 1) {
            return;
        }
        currentPage--;
        startRow = (currentPage - 1) * pageSize;
    }
    
    public void next() {
        if (currentPage < totalPages) {
            currentPage++;
        }
        startRow = (currentPage - 1) * pageSize;
    }
    
    public void last() {
        currentPage = totalPages;
        startRow = (currentPage - 1) * pageSize;
    }
    
    public void refresh(int _currentPage) {
        currentPage = _currentPage;
        if (currentPage > totalPages) {
            last();
        }
    }

	public Boolean isFirst() {
		return startRow<=0;
	}

	public Boolean isLast() {
		return startRow + pageSize >= totalRows;
	}
	
	public static void main(String[] args) {
		System.out.println("pageSize=".matches(".*pageSize.*"));
	}

	@Override
	public JSONObject toPageFooter() {
		JSONObject o = new JSONObject();
		o.put("totalCount", getTotalRows());
		o.put("totalPage", getTotalPages());
		o.put("pageSize",getPageSize());
		o.put("pgFrom", getStartRow());
		o.put("current", getCurrentPage());
		o.put("first", isFirst()?"1":"0");
		o.put("last",isLast()?"1":"0");
		return o;
	}
	 
}
