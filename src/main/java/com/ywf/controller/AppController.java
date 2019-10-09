package com.ywf.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ywf.pojo.User;
import com.ywf.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName AppComtroller
 * 功能: 控制器
 * Author yangweifeng
 * Date 2019-09-30 20:12
 * Version 1.0
 **/
@Controller
// @RestController 会对所有方法自动做json格式转换
@RequestMapping("/app")
public class AppController {
    @Autowired
    IUserService userService;
    /**
     *测试接口
     * http://localhost:8080/app/helloworld
     * @return json
     */
    @RequestMapping("/helloworld")
    @ResponseBody // 自动做json格式转换
    public Map<String,Object> showHelloWorld(){
        System.out.println("eeeeqqqq");
        Map<String,Object> map = new HashMap<>();
        map.put("111","helloworld");
        map.put("222","yangweifeng");
        map.put("333","helloworl1111d");
        return map;
    }

    /**
     * 文件上传
     * @return
     */
    @RequestMapping("/uploadfile")
    @ResponseBody // 自动做json格式转换
    public Map<String,Object> uploadfile(MultipartFile filename){
        System.out.println(filename.getOriginalFilename());
        try {
            filename.transferTo(new File("E:/" + filename.getOriginalFilename()));
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }

    /**
     * 添加用户
     * http://localhost:8080/app/adduser?name=ywf&age=18
     * @return
     */
    @RequestMapping("/adduser")
    @ResponseBody
    public Map<String,Object> addUser(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "age", required = true) Integer age){
        Map<String,Object> map = new HashMap<>();
        try {
            User user = new User();
            user.setName(name);
            user.setAge(age);
            this.userService.insertUser(user);
            map.put("msg","ok");
        }catch (Exception e){
             e.printStackTrace();
            map.put("msg","error");
        }
        return map;
    }

    /**
     * 添加用户
     * http://localhost:8080/app/searchalluser
     * @return
     */
    @RequestMapping("/searchalluser")
    @ResponseBody
    public String searchAllUser(){
        JSONObject jsonObject = new JSONObject();
        try {
            List<User> list = this.userService.selectUserAll();
            JSONArray jsonArray=new JSONArray();
            jsonArray.addAll(list);
            jsonObject.put("data",jsonArray);
            jsonObject.put("msg","ok");
        }catch (Exception e){
            e.printStackTrace();
            jsonObject.put("msg","error");
        }
        return jsonObject.toJSONString();
    }
    /**
     * 添加用户
     * http://localhost:8080/app/save
     * 开启对user对象的数据校验
     * bindingResult 封装了校验的结果
     * @ModelAttribute("user") 显示指定对象名称
     * @return
     */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(@RequestBody @ModelAttribute("user") @Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){ //true 为校验存在不合法的 不成功
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error: allErrors){
                System.out.println(error.getObjectName());
            }
        }
        System.out.println(user);
        return "OK";
    }
}
