package com.hegongshan.mooc.util;

import java.io.Serializable;
import java.util.List;

public class PageUtils<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer totalRows;
	private Integer currentPage;
	private Integer pageSize;
	@SuppressWarnings("unused")
	private Integer totalPages;
	@SuppressWarnings("unused")
	private Integer startPage;
	private List<T> list;

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * 设置当前页，若没有传入值，则赋值1
	 * @param currentPage 当前页数
	 */
	public void setCurrentPage(Integer currentPage) {
		if(currentPage == null) {
			this.currentPage = 1;
		} else {
			this.currentPage = currentPage;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPages() {
		return totalRows % pageSize == 0 ? totalRows / pageSize : totalRows / pageSize + 1;
	}

	public Integer getStartPage() {
		return (currentPage - 1) * pageSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
