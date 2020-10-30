package com.atguigu.jucdemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareDataOne{
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition cd = lock.newCondition();

    public void save(){
        lock.lock();

        try {
            while (number != 0){
                cd.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()
                    +"\t"+number);
            //通知
            cd.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }



    public  void delete(){
        lock.lock();
        try {
            while (number != 1){
                cd.await();
            }

            number--;

            System.out.println(Thread.currentThread().getName()
                    +"\t"+number);

            cd.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //这是用 synchronized同步锁 实现的
    /*public synchronized void save() throws InterruptedException {
        //判断
        while (number != 0){
           this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()
                            +"\t"+number);
        //通知
        this.notifyAll();

    }

    public synchronized void delete() throws InterruptedException {
        while (number != 1){
            this.wait();
        }

        number--;

        System.out.println(Thread.currentThread().getName()
                            +"\t"+number);

        this.notifyAll();
    }*/
}
/**
 *
 */
public class NotifyWaitDemo {
    public static void main(String[] args) {

        ShareDataOne shareDataOne = new ShareDataOne();

        /*new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.save();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.save();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.delete();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();*/

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                shareDataOne.save();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i <10; i++) {
                shareDataOne.delete();
            }
        },"BB").start();



    }


}
