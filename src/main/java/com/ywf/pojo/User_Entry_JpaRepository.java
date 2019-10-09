package com.ywf.pojo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClassName User_Entry_Repository
 * 功能: User_Entry,Integer (实体类型,主键类型)
 * 继承 PagingAndSortingRepository 继承的父接口较多
 * 对继承的父接口的方法的返回值进行了适配
 * 更加通用 开发中用的比较多的接口
 * Author yangweifeng
 * Date 2019-10-07 18:03
 * Version 1.0
 **/
public interface User_Entry_JpaRepository extends JpaRepository<User_Entry,Integer> {
}
