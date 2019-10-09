package com.ywf.pojo;
import javax.persistence.*;

/**
 * ClassName User_Entry
 * 功能: User_Entry  整合jpa-data
 * Author yangweifeng
 * Date 2019-10-07 17:52
 * Version 1.0
 **/
@Entity
@Table(name="user")
public class User_Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    @Override
    public String toString() {
        return "User_Entry{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }
}
