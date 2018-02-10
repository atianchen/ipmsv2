package com.yonyou.common.dto;

/**
 * Dto Convertor Factory 
 * @author jensen.chen
 *
 */
public class DtoConvertorFactory {

	
	public static DtoConvertor getDefaultConvertor()
	{
		return new SimpleDtoConvertor();
	}
}
