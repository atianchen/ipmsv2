package com.yonyou.recruit.dto.base;

import com.yonyou.common.dto.Dto;
import com.yonyou.common.dto.annotation.Property;

public class DeptDto implements Dto{

	private String id;

	private String name;
	
	private String name2;

	private String name3;
	
	private String code;
	
	@Property("parent.id")
	private String parentId;
	
	@Property("parent.code")
	private String parentCode;
	
	@Property("parent.name")
	private String parentName;
	
	@Property("parent.name1")
	private String parentName1;
	
	@Property("parent.name2")
	private String parentName2;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentName1() {
		return parentName1;
	}

	public void setParentName1(String parentName1) {
		this.parentName1 = parentName1;
	}

	public String getParentName2() {
		return parentName2;
	}

	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}
	
	
}
