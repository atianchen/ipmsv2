package com.yonyou.common.utils;

import java.security.MessageDigest;

/**
* @author jensen.chen
* @version 2017年9月19日 下午2:44:22
*/
public class EncryptUtils {
	
	private static    char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',    
		    'a', 'b', 'c', 'd', 'e', 'f' };  

	public final static String MD5(String s) {    
	   
	    try {    
	     byte[] strTemp = s.getBytes();    
	     MessageDigest mdTemp = MessageDigest.getInstance("MD5"); 
	     mdTemp.update(strTemp);    
	     byte[] md = mdTemp.digest();    
	     int j = md.length;    
	     char str[] = new char[j * 2];    
	     int k = 0;    
	     for (int i = 0; i < j; i++) {    
	        byte b = md[i];    
	        str[k++] = hexDigits[b >> 4 & 0xf];    
	        str[k++] = hexDigits[b & 0xf];    
	     }    
	     return new String(str);    
	    } catch (Exception e) {return null;}    
	}     
}
