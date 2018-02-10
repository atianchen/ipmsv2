package com.yonyou.common.dto.err;

public class ConvertException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	

	public ConvertException(String message)
	{
		super(message);
	}
	
	public ConvertException(Exception e)
	{
		super(e.getMessage());
	}


}
