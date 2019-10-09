package com.ywf.pojo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ClassName User_Entry_JPASpecificationExecutor
 * 功能: 提供了多条件查询的支持,并且可以在查询中添加分页和排序
 * 单独存在的
 * @Author yangweifeng
 * @Date 2019-10-08 16:38
 * @Version 1.0
 **/
public interface User_Entry_JPASpecificationExecutor extends JpaRepository<User_Entry,Integer>,JpaSpecificationExecutor<User_Entry> {

}


