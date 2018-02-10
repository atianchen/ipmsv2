package com.yonyou.recruit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yonyou.common.service.CrudService;
import com.yonyou.recruit.domain.base.Dept;
import com.yonyou.recruit.domain.base.Role;

public interface DeptService extends CrudService<Dept,String>{

	Page<Dept> listRole(Pageable page);
}
