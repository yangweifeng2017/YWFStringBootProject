package com.ywf.pojo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * ClassName User_Entry_Repository
 * 功能: Repository 接口
 * 1. 方法名称命名查询
 * 2. 基于@Query查询与更新
 * Author yangweifeng
 * Date 2019-10-07 18:36
 * Version 1.0
 **/
public interface User_Entry_Repository extends Repository<User_Entry,Integer> {
    /**
     * 方法名称命名查询
     * 方法名称必须遵循 驼峰是命名
     * findBy(关键字) + 属性名称(首字母大写) + 查询条件(首字母大写)
     * @param name
     * @return
     */
    List<User_Entry> findByName(String name);
    // 多条件查询
    List<User_Entry> findByNameAndAge(String name,Integer age);
    List<User_Entry> findByNameOrAge(String name,Integer age);
    // like查询
    List<User_Entry> findByNameLike(String name);

    /**
     * nativeQuery = true 直接执行标准sql
     * @param name
     * @return
     */
    @Query(value = "select id,name,age from user where name=?1",nativeQuery = true)
    List<User_Entry> queryByNameUseHql(String name);

    @Query(value = "update user set name=?1 where id = ?2",nativeQuery = true)
    @Modifying //需要执行更新操作
    List<User_Entry> updateUserById(String name,Integer id);


}
