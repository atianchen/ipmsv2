package com.yonyou.common.dto.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PropertyCache {

	private Map<Class<?>,EntityProperties> propertyMap;
	
	public PropertyCache()
	{
		this.propertyMap = new ConcurrentHashMap<Class<?>,EntityProperties>();
	}
	
	public void addLink(Class<?> arg,EntityProperties link)
	{
		this.propertyMap.put(arg, link);
	}
	
	public EntityProperties getEntityProperty(Class<?> entityClass)
	{
		return this.propertyMap.get(entityClass);
	}
	
}
