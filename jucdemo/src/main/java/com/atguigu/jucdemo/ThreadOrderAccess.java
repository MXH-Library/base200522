package com.atguigu.jucdemo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间按顺序调用，实现A->B->C
        * 三个线程启动，要求如下：
        * <p>
* AA打印5次，BB打印10次，CC打印15次
        * 接着
        * AA打印5次，BB打印10次，CC打印15次
        * ......来10轮
*/

class ShareData{

    private int number = 1;
    private Lock lock = new ReentrantLock();

    private Condition cd1 = lock.newCondition();
    private Condition cd2 = lock.newCondition();
    private Condition cd3 = lock.newCondition();

    public void printlnA(int total){
        lock.lock();

        try {
            //判断
            while(number != 1){
                cd1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()
                                +"\t"+total+"\t"+i);
            }
            //通知
            number = 2;
            cd2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void printlnB(int total){
        lock.lock();

        try {
            //判断
            while(number != 2){
                cd2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"\t"+total+"\t"+i);
            }
            //通知
            number = 3;
            cd3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printlnC(int total){
        lock.lock();

        try {
            //判断
            while(number != 3){
                cd3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()
                        +"\t"+total+"\t"+i);
            }
            //通知
            number = 1;
            cd1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.printlnA(i);
            }
                },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.printlnB(i);
            }
                },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.printlnC(i);
            }
                },"CC").start();
    }
}
