package com.atguigu.jucdemo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NotSafeDemo {

    //ArrayList() 线程不安全的实例
    public static void main1(String[] args) {
        List list = new CopyOnWriteArrayList(); //写时拷贝
            //Collections.synchronizedList(new ArrayList<>());

            //
            //new Vector
            //ArrayList();

        for (int i = 0; i < 17; i++) {
            new Thread(()->{
                 list.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(list);
                    },"AA").start();
        }
    }

    //HashSet()线程不安全的实例，要使用 CopyOnWriteArraySet()才能够保证线程安全
    public static void main2(String[] args) {
        Set set = new CopyOnWriteArraySet();
                //HashSet();
        for (int i = 0; i < 17; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,4));
                System.out.println(set);
                    },String.valueOf(i)).start();

        }
    }

    //HashMap线程不安全的实例
    public static void main(String[] args) {
        Map map = new ConcurrentHashMap();
                //HashMap();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                 map.put(UUID.randomUUID().toString().substring(0,4),Thread.currentThread().getName());
                System.out.println(map);
                    },String.valueOf(i)).start();
        }
    }



}
