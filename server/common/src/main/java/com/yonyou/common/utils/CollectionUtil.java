package com.yonyou.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

/**
* @author jensen.chen
* @version 2017年8月17日 上午10:24:09
*/
public class CollectionUtil {
	
	public static Map<String,String> convertArgsToMap(String...args)
	{
		Map<String,String> argMap = new HashMap<String,String>();
		if (args!=null)
			for (String arg : args)
			{
				argMap.put(arg, arg);
			}
		return argMap;
	}

	public static <T>Collection<T> convertPropertyList(Class<T> propType,Collection<?> list,String keyProperty)
	{
		Collection<T> rs = new ArrayList<T>();
		for(Object entity : list)
		{	
			try {
				rs.add((T)PropertyUtils.getProperty(entity, keyProperty));
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return rs;
	}
	
	public static <T>Map<String,T> convertListToObjMap(Collection<T> list,String keyProperty) throws Exception
	{
		Map<String ,T> rs = new HashMap<String,T>();
		String key = null;
		for (T entity : list)
		{	
			key = (String)PropertyUtils.getProperty(entity, keyProperty);
			if (key!=null)
			{
				if (!rs.containsKey(key))
					rs.put(key,entity);
			}
		}
		return rs;
	}
	
	public static <T>Map<String,List<T>> convertListToMap(Collection<T> list,String keyProperty) throws Exception
	{
		Map<String ,List<T>> rs = new HashMap<String,List<T>>();
		String key = null;
		for (T entity : list)
		{	
			key = (String)PropertyUtils.getProperty(entity, keyProperty);
			if (key!=null)
			{
				if (!rs.containsKey(key))
					rs.put(key, new ArrayList<T>());
				rs.get(key).add(entity);
			}
		}
		return rs;
	}
}
