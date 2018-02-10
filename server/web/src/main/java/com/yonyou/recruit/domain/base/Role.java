package com.yonyou.recruit.domain.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.yonyou.common.domain.BaseEntity;

@Entity
@Table(name="base_role")
public class Role extends BaseEntity{

	@Id
	@GeneratedValue(generator = "unitidGenerate")
	@GenericGenerator(name = "unitidGenerate", strategy = "uuid.hex")
	private String id;
	
	private String name;

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
	
	
}
