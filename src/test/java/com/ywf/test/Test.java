package com.ywf.test;

import com.ywf.pojo.*;
import com.ywf.service.impl.UserServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * ClassName Test
 * 功能: 测试类
 * @RunWith 启动器 SpringJUnit4ClassRunner SpringJUnit整合类
 * Author yangweifeng
 * Date 2019-10-06 18:15
 * Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {com.ywf.App.class}) // SpringBoot启动类 配置启动类 加载启动SpringBoot框架
public class Test {
    @Autowired
    private UserServiceImpl userService;
    @org.junit.Test
    public void TestSelectUserAll(){
        try {
            List<User> list = userService.selectUserAll();
            for (User user :list){
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @org.junit.Test
    public void TestRedis(){
      this.redisTemplate.opsForValue().set("ywf","yangweifeng");
      String value = (String) this.redisTemplate.opsForValue().get("ywf");
      System.out.println(value);
      User user = new User();
      user.setName("yangweifeng");
      user.setAge(18);
      user.setId(112);
      this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
      this.redisTemplate.opsForValue().set("user",user);

      this.redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
      User user1 = (User) this.redisTemplate.opsForValue().get("user");
      System.out.println(user1.toString());
      /*
       jSON格式存放
       */
      this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
      this.redisTemplate.opsForValue().set("user_json",user);

      this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
      User user2 = (User) this.redisTemplate.opsForValue().get("user_json");
      System.out.println(user2.toString());


    }

    /**
     * jpa 测试
     */
    @Autowired
    User_Entry_JpaRepository user_entry_Jpa_repository;
    @Autowired
    User_Entry_Repository user_Entry_Repository;
    @org.junit.Test
    @Transactional //事务支持
    // @Transactional 与 @org.junit.Test 一起使用时 事务自动回滚
    @Rollback(false) // 取消自动回滚
    public void TestJpaData(){
        /*
        User_Entry user_entry = new User_Entry();
        user_entry.setAge(99);
        user_entry.setName("jiaolimei");
        user_entry_Jpa_repository.save(user_entry);
        */
        List<User_Entry> list = user_Entry_Repository.queryByNameUseHql("ywf");
        for (User_Entry user_entry : list){
            System.out.println(user_entry.toString());
        }

    }
    @Autowired
    User_Entry_CrudRepository user_entry_crudRepository;
    @Autowired
    User_Entry_PagingAndSortRepository user_Entry_PagingAndSortRepository;
    @org.junit.Test
    public void TestJpaData1(){
        User_Entry user_entry = new User_Entry();
        user_entry.setAge(99);
        user_entry.setName("jiaolimei1");
        // 保存与更新操作 更新必须设置id值
        this.user_entry_crudRepository.save(user_entry);
        Optional<User_Entry> user_entry1 = this.user_entry_crudRepository.findById(4);
        List<User_Entry> list = (List<User_Entry>) this.user_entry_crudRepository.findAll();
        this.user_entry_crudRepository.deleteById(4);
        this.user_entry_crudRepository.deleteAll();
    }
    @org.junit.Test
    public void TestJpaData2(){
        // Order 定义排序规则
        /*
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order); // 参数为 order 集合 支持多字段排序
        List<User_Entry> list = (List<User_Entry>) user_Entry_PagingAndSortRepository.findAll(sort);
        for (User_Entry user_entry : list){
            System.out.println(user_entry);
        }*/
        // 分页
        /*
         分页参数 当前页是从 0 开始的
         */
        Integer page = 0; //当前页
        Integer size = 2; // 每页显示多少条
        Pageable pageable = new PageRequest(page,size);
        Page<User_Entry> page1 =  user_Entry_PagingAndSortRepository.findAll(pageable);
        System.out.println("总条数" + page1.getTotalElements());
        System.out.println("总页数" + page1.getTotalPages());
        System.out.println("当前页" + page1.getNumber());
        List<User_Entry> list = page1.getContent();
        for (User_Entry user_entry : list){
            System.out.println(user_entry);
        }
    }
    /*
      排序 + 分页
     */
    @org.junit.Test
    public void TestJpaData3(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order); // 参数为 order 集合 支持多字段排序
        Integer page = 1; //当前页
        Integer size = 2; // 每页显示多少条
        Pageable pageable = new PageRequest(page,size,sort);
        Page<User_Entry> page1 =  user_Entry_PagingAndSortRepository.findAll(pageable);
        System.out.println("总条数" + page1.getTotalElements());
        System.out.println("总页数" + page1.getTotalPages());
        System.out.println("当前页" + page1.getNumber());
        List<User_Entry> list = page1.getContent();
        for (User_Entry user_entry : list){
            System.out.println(user_entry);
        }
    }
    /*
      jpa
     */
    @org.junit.Test
    public void TestJpaData4(){
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order); // 参数为 order 集合 支持多字段排序
        Integer page = 1; //当前页
        Integer size = 2; // 每页显示多少条
        Pageable pageable = new PageRequest(page,size,sort);
        Page<User_Entry> page1 =  user_entry_Jpa_repository.findAll(pageable);
        System.out.println("总条数" + page1.getTotalElements());
        System.out.println("总页数" + page1.getTotalPages());
        System.out.println("当前页" + page1.getNumber());
        List<User_Entry> list = page1.getContent();
        for (User_Entry user_entry : list){
            System.out.println(user_entry);
        }
    }
    @Autowired
    User_Entry_JPASpecificationExecutor user_entry_jpaSpecificationExecutor;
    @org.junit.Test
    public void TestJpaData5(){
        // 单条件测试 封装查询条件
        /*
        Predicate 封装单个查询条件
         */
        Specification<User_Entry> specification1 = new Specification<User_Entry>() {
            /*
              Root<User_Entry> root 查询对象属性的封装
              CriteriaQuery<?> criteriaQuery 封装了要执行的查询中的各个部分的信息，select from  order by 等等
              CriteriaBuilder criteriaBuilder 查询条件的构造器 定义不同的查询条件
             */
            @Override
            public Predicate toPredicate(Root<User_Entry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'ywf'
                Predicate predicate = criteriaBuilder.equal(root.get("name"),"ywf"); //参数1 查询条件属性 参数2 查询条件的值
                return predicate;
            }
        };
        // 多条件查询
        Specification<User_Entry> specification2 = new Specification<User_Entry>() {
            /*
              Root<User_Entry> root 查询对象属性的封装
              CriteriaQuery<?> criteriaQuery 封装了要执行的查询中的各个部分的信息，select from  order by 等等
              CriteriaBuilder criteriaBuilder 查询条件的构造器 定义不同的查询条件
             */
            @Override
            public Predicate toPredicate(Root<User_Entry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'ywf' and age = 20
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("name"),"ywf")); //参数1 查询条件属性 参数2 查询条件的值
                list.add(criteriaBuilder.equal(root.get("age"),"16"));
                Predicate [] arr = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(arr));
            }
        };
        Specification<User_Entry> specification3 = new Specification<User_Entry>() {
            /*
              Root<User_Entry> root 查询对象属性的封装
              CriteriaQuery<?> criteriaQuery 封装了要执行的查询中的各个部分的信息，select from  order by 等等
              CriteriaBuilder criteriaBuilder 查询条件的构造器 定义不同的查询条件
             */
            @Override
            public Predicate toPredicate(Root<User_Entry> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // where name = 'ywf' and age = 20
                // criteriaBuilder.or(criteriaBuilder.equal(root.get("name"),"ywf"),criteriaBuilder.equal(root.get("age"),"16"));
                // or and 连用
                // criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),"ywf"),criteriaBuilder.equal(root.get("age"),"16"),criteriaBuilder.equal(root.get("id"),2));
                return criteriaBuilder.and(criteriaBuilder.equal(root.get("name"),"ywf"),criteriaBuilder.equal(root.get("age"),"16"));
            }
        };
        // 排序
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort = new Sort(order); // 参数为 order 集合 支持多字段排序


        List<User_Entry> list = this.user_entry_jpaSpecificationExecutor.findAll(specification2);
        for (User_Entry user_entry : list){
            System.out.println(user_entry);
        }
    }


}