package com.yonyou.common.domain;

import java.util.Collection;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class FlowEntity extends BaseEntity{

	private Integer flowStatus;

	public Integer getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(Integer flowStatus) {
		this.flowStatus = flowStatus;
	}
	
	@Override
	public Collection<String> excludeProperties(String...args)
	{
		return super.excludeProperties("flowStatus");
	}
}

