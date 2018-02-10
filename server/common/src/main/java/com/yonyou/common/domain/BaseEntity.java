package com.yonyou.common.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {

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
	
	public Collection<String> excludeProperties(String...args)
	{
		Collection<String> rs = new ArrayList<String>();
		rs.add("createTime");
		rs.add("updateTime");
		rs.add("id");
		if (args!=null)
		{
			for (String arg : args)
			{
				rs.add(arg);
			}
		}
		return rs;
	}
}
