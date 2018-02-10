package com.yonyou.common.controller;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.yonyou.common.utils.ConvertUtils;

/**
 * @author jensen
 */
public class DateEditor  extends PropertyEditorSupport{
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void setAsText(String text)   
	{
		if(StringUtils.isNotBlank(text)){
			try
			{
				this.setValue(ConvertUtils.convertToDate(text));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}   
	       
	public String getAsText()   
	{   
		if (this.getValue()!=null)
		{
			return format.format((Date)this.getValue());
		}
		else
		{
			return null;
		}
	} 
}

