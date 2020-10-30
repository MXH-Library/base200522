package com.atguigu.jucdemo;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName()+"Callable------");
        return 200;
    }
}

/**
 * 通过实现 Runnable()来获取线程和通过Callable()接口获取线程的区别
 *  Callable接口会 （1）抛异常 （2）有返回值 （3）实现的方法名字为 call
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       FutureTask<Integer> ft =  new FutureTask<Integer>(new MyThread2());
       new Thread(ft,"zhang3").start();

        System.out.println(ft.get());
        System.out.println(Thread.currentThread().getName());


    }
}
