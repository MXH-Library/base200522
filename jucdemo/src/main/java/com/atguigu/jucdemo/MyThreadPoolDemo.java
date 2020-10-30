package com.atguigu.jucdemo;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        //1.创建有固定线程数的线程,缺点：这种方法中的等待队列默认存储范围是 int的最大数值范围即21亿
//        ExecutorService threadPool = Executors.newFixedThreadPool(3);//例如：一个银行网点有3个窗口
//
//        try {
//            for (int i = 0; i < 7; i++) {
//                threadPool.execute(()->{
//                    System.out.println(Thread.currentThread().getName()
//                            +"业务员处理任务"
//                    );
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            threadPool.shutdown();
//        }
//
//        //System.out.println(Integer.MAX_VALUE);



//        //2.创建一个连接池内只有一个线程的连接池
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();
//        try {
//            for (int i = 0; i < 7; i++) {
//                threadPool1.execute(()->{
//                    System.out.println(Thread.currentThread().getName()
//                            +"业务员处理任务"
//                    );
//                });
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            threadPool1.shutdown();
//        }


        //3。创建可变参数的线程池,缺点：如果任务过多会创建无数个线程，会把内存撑爆
        ExecutorService threadPool2 = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 7; i++) {
                threadPool2.execute(()->{
                    System.out.println(Thread.currentThread().getName()
                            +"业务员处理任务"
                    );
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool2.shutdown();
        }
    }




}
