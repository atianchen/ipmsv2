package com.yonyou.common.repository.err;

public class PersistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	

	public PersistException(String message)
	{
		super(message);
	}
	
	public PersistException(Exception e)
	{
		super(e.getMessage());
	}


}