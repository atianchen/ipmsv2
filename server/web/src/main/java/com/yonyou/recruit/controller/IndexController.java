package com.yonyou.recruit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.common.controller.BaseController;
import com.yonyou.common.dto.DtoConvertor;

@Controller  
@RequestMapping("/")  
public class IndexController extends BaseController{
	
	@Autowired
	private DtoConvertor convertor;
	
	@RequestMapping("/")  
    public String index() {  
		return "index";
    }  
}
