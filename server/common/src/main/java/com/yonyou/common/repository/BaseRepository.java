package com.yonyou.common.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* @author jensen.chen
* @version 2017年8月9日 下午9:36:56
*/
public interface BaseRepository<T,K extends Serializable>  extends JpaRepository<T,K>{

	
}
