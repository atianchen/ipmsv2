package com.yonyou.common.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

/**
 * @author yonyou.jensen
 **/
public class ConvertUtils {

	public static Object convert(Object val,PropertyType type,String format)
	{
		if (type!=null)
		{
			if (StringUtils.isNotBlank(format))
			{
				if (type.equals(PropertyType.DateNumber))
				{
					Calendar cal = Calendar.getInstance();
					cal.setTimeInMillis((Long)val);
					return new SimpleDateFormat(format).format(cal.getTime());
				}
			}
		}
		return val;
	}
}
