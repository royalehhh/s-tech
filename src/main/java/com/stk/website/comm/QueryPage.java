package com.stk.website.comm;

import java.io.Serializable;

public class QueryPage implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 7290328507994026392L;
	
	// 定义默认每页中显示记录数
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	// 每页记录数
	private int pageSize = DEFAULT_PAGE_SIZE;

	// 总页数
	private long pageCount = 1;

	// 总记录数
	private int recordCount = 0;

	// 目标页
	private int targetPage = 1;

	/**
	 * 排序字段
	 */
	private String order;

	/**
	 * 排序方式(asc,desc)
	 */
	private String sort;
	
	public QueryPage() {
		
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		this.pageSize = pageSize;
	}

	public long getPageCount() {
		return pageCount;
	}

	public void setPageCount(long pageCount) {
		
		this.pageCount = pageCount;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		
		// 总页数
		int pageCount = recordCount / pageSize;
		if (recordCount % pageSize > 0) {
			pageCount++;
		}
		this.pageCount = pageCount;
	}
	
	public void setRecordCount(long recordCount) {
		this.setRecordCount(Integer.parseInt(String.valueOf(recordCount)));
	}

	public int getTargetPage() {
		return targetPage;
	}

	public void setTargetPage(int targetPage) {
		if (targetPage <= 0) {
			targetPage = 1;
		}
		this.targetPage = targetPage;
	}

	public long getLimitStart() {
		return (targetPage - 1) * pageSize;
	}
	
	public int getLimitEnd() {
		return pageSize;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
}