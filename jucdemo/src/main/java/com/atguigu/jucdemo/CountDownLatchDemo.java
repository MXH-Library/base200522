package com.atguigu.jucdemo;

import com.atguigu.jucdemo.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 6位同学离开教室后，班长锁门离开教室 (秦灭六国)
 */
public class CountDownLatchDemo {
   /* public static void main1(String[] args) throws InterruptedException {
       CountDownLatch cd =  new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()
                                +"号同学离开教室");
                cd.countDown();
                    },String.valueOf(i)).start();
        }

        cd.await();
        System.out.println(Thread.currentThread().getName()+"班长锁门离开教室");
    }*/

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd =  new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()
                        +"国·被灭");
                cd.countDown();
            }, CountryEnum.foreach_CountryEnum(i).getMessage()).start();
        }

        cd.await();
        System.out.println(Thread.currentThread().getName()+"秦灭六国一统天下");
    }
}
