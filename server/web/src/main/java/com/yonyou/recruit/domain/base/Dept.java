package com.yonyou.recruit.domain.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yonyou.common.domain.BaseEntity;

@Entity
@Table(name="base_dept")
public class Dept extends BaseEntity{

	@Id
	@GeneratedValue(generator = "unitidGenerate")
	@GenericGenerator(name = "unitidGenerate", strategy = "uuid.hex")
	private String id;
	
	/**
	 * chs
	 */
	private String name;
	
	/**
	 * cht
	 */
	private String name2;
	
	/**
	 * eng
	 */
	private String name3;
	
	private String code;
	
	@ManyToOne
	private Dept parent;

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

	public Dept getParent() {
		return parent;
	}

	public void setParent(Dept parent) {
		this.parent = parent;
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
	
	
	
}
