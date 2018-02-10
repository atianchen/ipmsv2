package com.yonyou.common.dto.cache;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;

import com.yonyou.common.dto.ConvertUtils;
import com.yonyou.common.dto.PropertyType;
import com.yonyou.common.dto.err.ConvertException;

public class PropertyLink {

	private Collection<String> fields;
	
	private PropertyType type;
	
	private String format;
	
	public PropertyLink()
	{
		this.fields = new ArrayList<String>();
	}
	
	public PropertyLink(String field)
	{
		this();
		this.fields.add(field);
	}
	
	
	
	public PropertyType getType() {
		return type;
	}

	public String getFormat() {
		return format;
	}

	public void setType(PropertyType type) {
		this.type = type;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void addField(String field)
	{
		this.fields.add(field);
	}
	
	public Object val(Object entity)
	{
		try
		{
			if (entity==null) return null;
			Object val = null;int level = 0;
			for (String field : fields)
			{
				if (val==null && level>0)return null;
				val = (val==null)?PropertyUtils.getProperty(entity, field):PropertyUtils.getProperty(val, field);
				level++;
			}
			if (val!=null && type!=null && type.equals(PropertyType.DateNumber))
			{
				return ConvertUtils.convert(val,type, format);
			}
			return val;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ConvertException(e);
		}
	}
}
