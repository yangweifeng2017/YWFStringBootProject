package com.ywf.pojo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * ClassName User
 * Author yangweifeng
 * Date 2019-10-02 07:50
 * @Email 对邮箱的判断是否合法 支持正则表达式的匹配
 * 还有更多的校验规则...可以尝试找找
 * Version 1.0
 **/
public class User implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @NotBlank(message = "名称不能为空") //非空校验 + 提示信息 会去出两边的空格
    @NotEmpty //不会去出两边的空格
    @Length(min = 6,max = 12)  // 长度校验
    private String name;

    @Min(value = 0) // 判断最大值 最小值
    @Max(value = 100)
    private Integer age;

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
