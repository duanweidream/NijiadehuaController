package com.nijiadehua.api.model;

import java.util.List;

public class Page {
	public Integer totalCount=0,startIndex=0,itemCount=10,currentPage=0,totalPage=0;
	public List<?> list=null;
	
	public Page(Integer currentPage){
		this.currentPage=currentPage;
		this.startIndex=(currentPage - 1)*itemCount;
	}
	
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		this.totalPage=totalCount%itemCount==0?totalCount/itemCount:(totalCount/itemCount)+1;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	
}
