package com.yonyou.common.dto;

import java.util.HashMap;
import java.util.Map;

/**
* @author jensen.chen
* @version 2017年8月11日 下午4:28:27
*/
public class AttrMap {

	protected Map<String,Object> params;
	
	public Map<String,Object> getParams() {
		return params;
	}

	public void setParams(Map<String,Object> params) {
		this.params = params;
	}
	
	public void addParam(String key,Object val)
	{
		if (params==null)
			params = new HashMap<String,Object>();
		this.params.put(key, val);
	}
}
