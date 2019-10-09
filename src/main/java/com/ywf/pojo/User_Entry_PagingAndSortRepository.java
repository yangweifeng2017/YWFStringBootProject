package com.ywf.pojo;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ClassName User_Entry_PagingAndSortRepository
 * 功能: 提供分页和排序的功能 该接口继承了CrudRepository
 *
 * @Author yangweifeng
 * @Date 2019-10-08 14:42
 * @Version 1.0
 **/
public interface User_Entry_PagingAndSortRepository extends PagingAndSortingRepository<User_Entry,Integer> {
}
