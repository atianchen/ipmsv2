package com.yonyou.common.dto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.yonyou.common.dto.annotation.Ignore;
import com.yonyou.common.dto.annotation.Property;
import com.yonyou.common.dto.cache.EntityProperties;
import com.yonyou.common.dto.cache.PropertyLink;
import com.yonyou.common.utils.CollectionUtil;

/**
 * Domain Field Resolver
 * @author jensen
 *
 */
public class Resolver {
	
	private static final String PROPERTY_DOT = "\\.";

	public static EntityProperties getEntityProperty(Class<?> entityClass,Class<?> targetClass)
	{
		try
		{
			EntityProperties prop = new EntityProperties();
			Collection<Field> fields = getFields(entityClass); 
			Collection<Field> targetFields = getFields(targetClass); 
			Map<String,Field> targetFieldMap = CollectionUtil.convertListToObjMap(targetFields, "name");
			for (Field field : fields)
			{
				try
				{
					if (field.getAnnotation(Ignore.class)==null)
					{
						if (field.getAnnotation(Property.class)!=null)
						{
							Property property = field.getAnnotation(Property.class);
							String propStr = property.value();
							String[] props = propStr.split(PROPERTY_DOT);
							PropertyLink pl = new PropertyLink();
							if (property.type()!=null)
							{
								pl.setType(property.type());
							}
							if (StringUtils.isNotBlank(property.format()))
							{
								pl.setFormat(property.format());
							}
							Class<?> propType = targetClass;
							for (String pr : props)
							{
								pl.addField(pr);
								if (props.length>1)
									propType = propType.getDeclaredField(pr).getType();
							}	
							prop.addLink(field,pl);
						}
						else
						{
							prop.addLink(field,new PropertyLink(field.getName()));
						}
					}
				}
				catch (Exception e)
				{
					e.printStackTrace();continue;
				}
			}

			return prop;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static Collection<Field> getFields(Class<?> entityClass)
	{
		Collection<Field> fieldList = new ArrayList<Field>();
		Class<?> tempClass = entityClass;
		while (tempClass != null) {
		      fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
		      tempClass = tempClass.getSuperclass(); 
		}
		return fieldList;
	}
}
 