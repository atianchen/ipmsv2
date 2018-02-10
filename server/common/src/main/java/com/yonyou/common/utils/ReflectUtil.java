package com.yonyou.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
* @author jensen.chen
* @version 2017年8月17日 上午10:39:58
*/
public class ReflectUtil {

	public static Class<?> getCollectionGenericType(Field f)
	{
	    Type fc = f.getGenericType(); 
        if(fc == null) return null;  
        if(fc instanceof ParameterizedType) 
        {   
              ParameterizedType pt = (ParameterizedType) fc;  
              return (Class<?>)pt.getActualTypeArguments()[0]; 
        }
        else
        {
        	return null;
        }
	}
}
