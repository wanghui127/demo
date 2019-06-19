/*
package com.example.demo;


import com.example.demo.utils.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void contextLoads() {
        user user = null;
        Optional.ofNullable(user).map(user1 -> user.getUserName()).orElse("unknown");

        System.err.println(user.getUserName());
    }

    @Test
    public void lambda() {
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        aList.add(5);
        aList.add(3);
        aList.add(4);
        aList.add(2);
        aList.stream().forEach(user -> {
            //System.out.println(user);
        });
        //aList.forEach((a)->System.out.println(a));
        aList.forEach(System.out::println);

        aList.forEach(user -> {
            System.out.println(user);
        });
    }


    public void createUser(user user) {

        Optional<user> user1 = Optional.ofNullable(user);
        //user1.orElseGet(user);
        user aa = user1.orElse(new user());
        System.err.println(aa);

    }

    @Test
    public void optional() {
        user user = new user();
        //user.setId(2);
        //user.setAddress("a");
        //user.setUserName("b");
        createUser(user);
    }


    // redis工具类测试
    @Test
    public void test() {
        //redisTemplate.opsForValue().set("4","444");
        //redisUtil.del("4","3");
        redisUtil.set("2", 222);
        System.err.println("success");
    }



    //MD5加盐测试
    @Test
    public void md5() {
        //String password1 = Md5Code.generate("wanghui");
        //271d3b07db1be0cc4d68e094c6b24f06314f766471d88c15
        //System.err.println("wanghui加密后:"+Md5Code.generate("wanghui"));
        //System.err.println(Md5Code.verify("wanghui",Md5Code.generate("wanghui")));

    }

}
*/
