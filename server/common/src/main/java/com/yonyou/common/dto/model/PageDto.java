package com.yonyou.common.dto.model;

import java.util.List;

public class PageDto<T> extends BaseDto{

	private static final long serialVersionUID = 1L;

	private int page;
	
	private int size;
	
	private int totalSize;
	
	private int totalPage;
	
	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	
	private List<T> items;
	
	
	public PageDto()
	{
		
	}
	
	public PageDto(int page,int size,int totalSize,int totalPage)
	{
		this.page = page;
		this.size = size;
		this.totalSize = totalSize;
		this.totalPage = totalPage;
	}
	
	public PageDto(int page,int size,int totalSize,int totalPage,List<T> items)
	{
		this(page,size,totalSize,totalPage);
		this.items = items;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}
	

	

	
}

