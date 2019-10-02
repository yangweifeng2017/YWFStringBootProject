package com.ywf.mapper;

import com.ywf.pojo.User;

import java.util.List;

/**
 * ClassName IUserMapper
 * 功能: TODO
 *
 * @Author yangweifeng
 * @Date 2019-10-02 07:53
 * @Version 1.0
 **/
public interface UserMapper {
    void insertUser(User user) throws Exception;
    List<User> selectUserAll() throws Exception;
    User selectUserById(Integer id) throws Exception;
    void UpdateUser(User user) throws Exception;
    void deleteUserById(Integer id) throws Exception;


}
