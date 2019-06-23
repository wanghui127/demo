package com.example.demo.utils;
import java.util.UUID;
/**
 * @program: uuid生成工具类
 * @description:
 * @author: Hui.Wang
 * @Time: $
 * @create: 2019-06-23 13:51
 **/
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println("格式前的UUID ： " + UUID.randomUUID().toString());
        System.out.println("格式化后的UUID ：" + getUUID());
    }

}
