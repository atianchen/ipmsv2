package com.yonyou.common.service;

/**
* @author jensen.chen
* @version 2017年8月3日 下午3:04:42
*/
public interface CrudService<T,K> {

	T save(T entity);
	
	void delete(T entity);
	
	T get(K id);
}
