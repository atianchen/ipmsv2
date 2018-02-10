package com.yonyou.common.service;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.common.domain.BaseEntity;

/**
* @author jensen.chen
* @version 2017年9月11日 下午4:40:06
*/
public abstract class SimpleCrudService<T,K extends Serializable> {
	
	protected abstract JpaRepository<T,K> getRepository();

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public T save(T entity)
	{
		if (entity instanceof BaseEntity)
		{
			BaseEntity e = (BaseEntity)entity;
			if (e.getCreateTime()==null)e.setCreateTime(Calendar.getInstance().getTimeInMillis());
			else if (e.getUpdateTime()==null)e.setUpdateTime(Calendar.getInstance().getTimeInMillis());
		}
		return this.getRepository().saveAndFlush(entity);
	}
	

	public  T get(K id)
	{
		return this.getRepository().getOne(id);
	}
	


	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public void delete(T entity)
	{
		this.getRepository().delete(entity);
	}
}
