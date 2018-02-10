package com.yonyou.common.service;

public interface BaseService<T> {

	T save(T entity);
	
	T get(String id);
	
	void remove(T entity);
	
	
}
