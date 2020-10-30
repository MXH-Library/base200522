package com.atguigu.jucdemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{
    private int ticket = 30;

    private Lock lock = new ReentrantLock(); //可重用锁

    public void sale(){
        lock.lock();
        try {
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()
                        +"已卖出"+(ticket--)+"号票\t还剩余"+ticket+"张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


//    public synchronized void sale(){
//        if(ticket>0){
//            System.out.println(Thread.currentThread().getName());
//        }
//    }
}

public class SaleTicket {
    /**
     * 买票程序复习线程知识：三个售票员 卖出  30张票
     *
     * 线程  操作  资源类 ：先创建资源类 在资源类里面写入操作，最后去 new Thread
     */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();


        //第二种 实现方式 匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        },"C").start();


        //第三种 Lambda表达式
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CC").start();
    }
}
