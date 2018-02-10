package com.yonyou.recruit.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.common.service.SimpleCrudService;
import com.yonyou.recruit.domain.base.Role;
import com.yonyou.recruit.repository.base.RoleRepository;
import com.yonyou.recruit.service.RoleService;

@Service("corpTypeService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class RoleServiceImpl extends SimpleCrudService<Role,String> implements RoleService{

	@Autowired
	private RoleRepository repository;
	
	public Page<Role> listRole(Pageable page) {
		return repository.listRole(page);
	}

	@Override
	protected JpaRepository<Role, String> getRepository() {
		return repository;
	}

}
