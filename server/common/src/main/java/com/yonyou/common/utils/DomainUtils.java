package com.yonyou.common.utils;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;

import com.yonyou.common.domain.BaseEntity;

/**
* @author jensen.chen
* @version 2017年8月15日 上午10:23:39
*/
public class DomainUtils {


	/**
	 * 属性拷贝
	 * 不拷贝复合属性
	 * @param dest 目的
	 * @param src 来源
	 * @param excludes 排除的属性
	 */
	public static <T>T copyProperties(T dest,Object src ,String...ignoreProperties)
	{
		Collection<String> ignoreList = Arrays.asList(ignoreProperties);
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(src.getClass());
		for (PropertyDescriptor des : descriptors)
		{
			try
			{
				if (!BaseEntity.class.isAssignableFrom(des.getPropertyType()) && !Collection.class.isAssignableFrom(des.getPropertyType())  &&PropertyUtils.isReadable(src, des.getName())  && !ignoreList.contains(des.getName()))
				{
					PropertyUtils.setProperty(dest, des.getName(), PropertyUtils.getProperty(src, des.getName()));
				}
			}
			catch (Exception e)
			{
				continue;
			}
		}
		return dest;
	}
	
	
	/**
	 * 属性拷贝
	 * @param dest 目的
	 * @param src 来源
	 * @param excludes 排除的属性
	 */
	public static <T>T copyFullProperties(T dest,Object src ,String...ignoreProperties)
	{
		Collection<String> ignoreList = Arrays.asList(ignoreProperties);
		PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(src.getClass());
		for (PropertyDescriptor des : descriptors)
		{
			try
			{
				if (PropertyUtils.isReadable(src, des.getName())  &&  !ignoreList.contains(des.getName()))
					PropertyUtils.setProperty(dest, des.getName(), PropertyUtils.getProperty(src, des.getName()));
			}
			catch (Exception e)
			{
				continue;
			}
		}
		return dest;
	}
}
