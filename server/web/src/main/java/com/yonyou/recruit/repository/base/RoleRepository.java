package com.yonyou.recruit.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yonyou.recruit.domain.base.Role;

public interface RoleRepository extends JpaRepository<Role,String>,JpaSpecificationExecutor<Role>{
	
	 @Query( value = " select r from Role r", 
			 countQuery = " select count(r) from Role r" )
	 Page<Role> listRole(Pageable page); 

}
