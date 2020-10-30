package com.atguigu.jucdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDataA{

    private Lock lock = new ReentrantLock();
    private Lock lock1 = new ReentrantLock();


    public void getA(){
        lock.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"\t"+"试图抢占B锁");
            this.getB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }




    }

    public void getB() {
        lock1.lock();
        System.out.println(Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"\t"+"试图抢占A锁");
            this.getA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }
}

public class TestClass {
//    public static void main1(String[] args) {
//        int i = 1;
//        System.out.println(i);
//        System.out.println(++i);
//        System.out.println(i++);
//    }


    //死锁实例
    static Object objectLockA = new Object();
    static Object objectLockB = new Object();

    public static void main(String[] args) {



        new Thread(()->{
            synchronized (objectLockA){



                System.out.println(Thread.currentThread().getName()+"\t"+"自己持有A锁,等待获得B锁");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLockB){
                    System.out.println(Thread.currentThread().getName()+"\t"+"获得B锁成功");
                }

            }

        },"A").start();

        new Thread(()->{
            synchronized (objectLockB){
                System.out.println(Thread.currentThread().getName()+"\t"+"自己持有A锁,等待获得B锁");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName()+"\t"+"获取A锁成功");
                }

            }

        },"B").start();

       /* ShareDataA shareDataA = new ShareDataA();

        new Thread(()->{
                shareDataA.getA();
                },"AA").start();

        new Thread(()->{
                 shareDataA.getB();
                },"BB").start();*/
    }



}
