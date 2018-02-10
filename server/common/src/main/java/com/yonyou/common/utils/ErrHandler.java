package com.yonyou.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
* @author jensen.chen
* @version 2017年9月19日 下午5:48:14
*/
public class ErrHandler {

	public static String errToString(Exception e)
	{
		StringWriter sw = new StringWriter();    
		PrintWriter pw = new PrintWriter(sw);    
		e.printStackTrace(pw);    
		return sw.toString(); 
	}
}
