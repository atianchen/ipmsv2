package com.yonyou.common.dto.cache;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityProperties {
	
	private Map<Field,PropertyLink> propertyMap;
	
	public EntityProperties()
	{
		this.propertyMap = new ConcurrentHashMap<Field,PropertyLink>();
	}
	
	public void addLink(Field arg,PropertyLink link)
	{
		this.propertyMap.put(arg, link);
	}
	
	public void addField(Field arg,Field target)
	{
		this.propertyMap.put(arg, new PropertyLink(target.getName()));
	}
	
	public Map<Field,PropertyLink> getPropertyMap()
	{
		return this.propertyMap;
	}
}
