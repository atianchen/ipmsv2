package com.yonyou.common.dto;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.yonyou.common.domain.BaseEntity;

public interface DtoConvertor {
	
	<T>Collection<T> convertFromMapList(Class<T> entityCls,List<Map<?,?>> mapList,String... ignoreProperties);
	
	<T>T convertFromMap(Class<T> entityCls,Map<?,?> map,String... ignoreProperties);
	
	
	<T extends BaseEntity>void convertFrom(T target,Object dto,String... ignoreProperties);
	/**
	 * Convert Dto to Jo
	 * @param target
	 * @param dto
	 * @param ignoreProperties
	 * @return
	 */
	<T extends BaseEntity>T convertFrom(Class<T> target,Object dto,String... ignoreProperties);

	/**
	 * Convert Jo to Dto
	 * @param target
	 * @param entity
	 * @param ignoreProperties
	 * @return
	 */
	<T>T convertTo(Class<T> target,Object entity,String... ignoreProperties);
	
	/**
	 * Convert Jo list to Dto list
	 * @param target
	 * @param entities
	 * @param ignoreProperties
	 * @return
	 */
	<T>List<T> convertListTo(Class<T> target,Collection<?> entities,String... ignoreProperties);

	
	
}
