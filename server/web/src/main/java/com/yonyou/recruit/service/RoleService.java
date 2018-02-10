package com.yonyou.recruit.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yonyou.common.service.CrudService;
import com.yonyou.recruit.domain.base.Role;

public interface RoleService extends CrudService<Role,String>{

	Page<Role> listRole(Pageable page);
}
