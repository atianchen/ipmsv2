package com.yonyou.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yonyou.common.dto.PropertyType;


public class ConvertUtils {

	private static 	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static 	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
	private static 	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
	private static 	SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
	private static 	SimpleDateFormat format4 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static 	SimpleDateFormat format5 = new SimpleDateFormat("yyyyMMdd");
	private static 	SimpleDateFormat format6 = new SimpleDateFormat("yyyyMMddHHmm");
	private static 	SimpleDateFormat format7 = new SimpleDateFormat("yyyyMMddHHmmss");

	public static String convertToDbTime(Date date)
	{
		return format.format(date)+".0";
	}
	
	public static Class<?> getTypeClass(PropertyType type) throws Exception
	{
		if (type.equals(PropertyType.String))
			return String.class;
		else if (type.equals(PropertyType.Integer))
			return Integer.class;
		else if (type.equals(PropertyType.Long))
			return Long.class;
		else if (type.equals(PropertyType.Double))
			return Double.class;
		else if (type.equals(PropertyType.Float))
			return Float.class;
		else if (type.equals(PropertyType.Integer))
			return Integer.class;
		else if (type.equals(PropertyType.Date))
			return java.util.Date.class;
		else if (type.equals(PropertyType.TimeInMillis))
			return java.util.Date.class;
		else
		{
			return null;
		}
	}
	
	
	public static Class<?> getTypeClass(String type) throws ClassNotFoundException
	{
		if (type.equals("string"))
			return String.class;
		else if (type.equals("short"))
			return Short.class;
		else if (type.equals("long"))
			return Long.class;
		else if (type.equals("double"))
			return Double.class;
		else if (type.equals("float"))
			return Float.class;
		else if (type.equals("int"))
			return Integer.class;
		else if (type.equals("date"))
			return java.util.Date.class;
		else if (type.equals("datenum"))
			return java.util.Date.class;
		else
		{
			return Class.forName(type);
		}
	}
	
	public static Object convert(Object value ,PropertyType type,String format) throws Exception
	{
		if(value!=null){
		if (type.equals(PropertyType.TimeInMillis))
			return CalendarUtils .formatDateStr(value.toString(), format).getTime();
		else if (type.equals(PropertyType.Date))
			return CalendarUtils.formatDateStr(value.toString(), format);
		else if (type.equals(PropertyType.Integer))
			return Integer.valueOf(value.toString());
		else if (type.equals(PropertyType.Double))
			return Double.valueOf(value.toString());
		else if (type.equals(PropertyType.Long))
			return Long.valueOf(value.toString());
		else if (type.equals(PropertyType.Float))
			return Float.valueOf(value.toString());
		}
		return value;
	}
	
	public static Object convert(Object value ,Class<?> cls,String type,String format) throws Exception
	{
		if(value!=null){
		if (type.equals("datenum"))
			return CalendarUtils .formatDateStr(value.toString(), format).getTime();
		
		if (cls.equals(String.class))
			return value.toString();
		else if (cls.equals(java.util.Date.class))
			return CalendarUtils.formatDateStr(value.toString(), format);
		else if (cls.equals(Integer.class))
			return Integer.valueOf(value.toString());
		else if (cls.equals(Double.class))
			return Double.valueOf(value.toString());
		else if (cls.equals(Short.class))
			return Short.valueOf(value.toString());
		else if (cls.equals(Long.class))
			return Long.valueOf(value.toString());
		else if (cls.equals(Float.class))
			return Float.valueOf(value.toString());
		}
		return value;
	}
	
	public static Date convertToDate(String text)   
	{
		if(text != null && (!"".equals(text))){
			try
			{
				if (text.trim().length() == 4)
					return format3.parse(text);
				else if (text.trim().length()==8 && text.indexOf("-")<0)
					return format5.parse(text);
				else if (text.trim().length()==12 && text.indexOf("-")<0)
					return format6.parse(text);
				else if (text.trim().length()==14 && text.indexOf("-")<0)
					return format7.parse(text);
				else if (text.trim().length() == 7 || text.trim().length() == 6)
					return format2.parse(text);
				else if (text.trim().length() >=8  &&  text.trim().length() <=10)
					return format1.parse(text);
				else if (text.trim().length() <=20)
					return format4.parse(text);
				else
					return format.parse(text);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return null;
			}
		}
		else
		{
			return null;
		}
	}   
}
