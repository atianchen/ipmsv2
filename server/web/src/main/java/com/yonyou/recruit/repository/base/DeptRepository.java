package com.yonyou.recruit.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yonyou.recruit.domain.base.Dept;

public interface DeptRepository extends JpaRepository<Dept,String>,JpaSpecificationExecutor<Dept>{

	 @Query( value = " select d from Dept d", 
			 countQuery = " select count(d) from Dept d" )
	 Page<Dept> listDept(Pageable page); 
}
