package com.ywf.service.impl;

import com.ywf.mapper.UserMapper;
import com.ywf.pojo.User;
import com.ywf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName UserServiceImpl
 * 功能: TODO
 * 运行方式与参数: TODO
 * Author yangweifeng
 * Date 2019-10-02 08:03
 * Version 1.0
 **/
@Service("UserServiceImpl")
@Transactional //受事务控制
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void insertUser(User user) throws Exception {
        this.userMapper.insertUser(user);
    }
    @Override
    public List<User> selectUserAll() throws Exception {
        return this.userMapper.selectUserAll();
    }
    @Override
    public User selectUserById(Integer id) throws Exception {
        return this.userMapper.selectUserById(id);
    }
    @Override
    public void UpdateUser(User user) throws Exception {
        this.userMapper.UpdateUser(user);
    }
    @Override
    public void deleteUserById(Integer id) throws Exception {
        this.userMapper.deleteUserById(id);
    }
}
