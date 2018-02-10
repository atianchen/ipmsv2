package com.yonyou.common.dto;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import com.itc.util.ConvertUtils;

import com.yonyou.common.domain.BaseEntity;
import com.yonyou.common.dto.cache.EntityProperties;
import com.yonyou.common.dto.cache.PropertyCache;
import com.yonyou.common.dto.cache.PropertyLink;
import com.yonyou.common.dto.err.ConvertException;
import com.yonyou.common.utils.CollectionUtil;
import com.yonyou.common.utils.ReflectUtil;

/**
 * Dto Convertor
 * @author jensen
 *
 */
public class SimpleDtoConvertor implements DtoConvertor{
	
	private static PropertyCache cache = new PropertyCache();
	


	public <T extends BaseEntity>void convertFrom(T target,Object dto,String... ignoreProperties)
	{
		BeanUtils.copyProperties(dto, target, target.excludeProperties(ignoreProperties).toArray(new String[]{}));
	}
	
	public <T extends BaseEntity>T convertFrom(Class<T> targetClass,Object dto,String... ignoreProperties)
	{
		T target = (T) BeanUtils.instantiate(targetClass);
		BeanUtils.copyProperties(dto, target, ignoreProperties);
		return target;
	}
	
	@Override
	public <T>List<T> convertListTo(Class<T> target,Collection<?> entities,String... ignoreProperties)
	{ 
		List<T> rs = new ArrayList<T>();
		for (Object entity : entities)
		{
			rs.add(convertTo(target,entity,ignoreProperties));
		}
		return rs;
	}
	

	@Override
	public <T> T convertTo(Class<T> target, Object entity,String... ignoreProperties) {
		try
		{	
			Map<String,String> am = CollectionUtil.convertArgsToMap(ignoreProperties);
			EntityProperties prop = cache.getEntityProperty(target);
			if (prop==null)
			{
				prop = Resolver.getEntityProperty(target, entity.getClass());
				synchronized(cache)
				{
					cache.addLink(target, prop);
				}
			}
			T obj = target.newInstance();
			Map<Field,PropertyLink> propMap = prop.getPropertyMap();
			for (Field field : propMap.keySet())
			{
				try {
					if (!am.containsKey(field.getName())&& !field.getName().equals("serialVersionUID")  && ! Modifier.isStatic(field.getModifiers()))  
					{
						field.setAccessible(true);
						if (Collection.class.isAssignableFrom(field.getType()))
						{
							Class<?> srcClass = ReflectUtil.getCollectionGenericType(field);
							Collection rs = (Collection)propMap.get(field).val(entity);
							if (rs!=null && rs.size()>0)
							{
								field.set(obj, this.convertListTo(srcClass, rs, ignoreProperties));
							}
						}
						else if (Dto.class.isAssignableFrom(field.getType()))
						{
							if (propMap.get(field).val(entity)!=null)
								field.set(obj, this.convertTo(field.getType(), propMap.get(field).val(entity), ignoreProperties));
						}
						else
						{
							field.set(obj, propMap.get(field).val(entity));
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}	
			}
		
			return obj;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ConvertException(e);
		}
	}

	public 	<T>Set<T> convertFromMapList(Class<T> entityCls,List<Map<?,?>> mapList,String... ignoreProperties)
	{
		Set<T> list = new HashSet<T>();
		for (Map<?,?> data : mapList)
		{
			list.add(convertFromMap(entityCls,data,ignoreProperties));
		}
		return list;
	}
	
	public <T>T convertFromMap(Class<T> target,Map<?,?> map,String... ignoreProperties)
	{
		try
		{
			Collection<Field> fields = Resolver.getFields(target);
			T entity = target.newInstance();
			for (Field field: fields)
			{
				if (map.containsKey(field.getName()))
				{
					if (Collection.class.isAssignableFrom(field.getType()))
					{
						PropertyUtils.setProperty(entity,field.getName(),convertFromMapList(ReflectUtil.getCollectionGenericType(field),
								(List<Map<?,?>>)map.get(field.getName())));
					}
					else
					{
						PropertyUtils.setProperty(entity, field.getName(), ConvertUtils.convert(map.get(field.getName()),field.getType(),field.getType().getName(),null));
					}
				}
			}
			return entity;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ConvertException(e);
		}
	}
}
