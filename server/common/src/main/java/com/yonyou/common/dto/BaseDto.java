package com.yonyou.common.dto;

/**
* @author jensen.chen
* @version 2017年8月8日 下午12:08:19
*/
public class BaseDto implements Dto{

	  
	private Long createTime;
	
	  
	private Long updateTime;


	public Long getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}


	public Long getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
}
