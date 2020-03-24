package com.demo;

/**
 * @author linhua
 * @apiNote
 * @date 2020-03-24 12:08 下午
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    private ExecutorService executorService;
    private CountDownLatch countDownLatch;
    private int parties;

    public static void main(String[] args){
        CountDownLatchExample countDownLatchExample = new CountDownLatchExample(10);
        try {
            countDownLatchExample.example();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CountDownLatchExample(int parties) {
        executorService = Executors.newFixedThreadPool(parties);
        countDownLatch = new CountDownLatch(parties);
        this.parties = parties;
    }

    public void example() throws InterruptedException {
        for (int i = 0; i < parties; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " gets job done");
                countDownLatch.countDown(); // 线程完成任务之后countDown
            });
        }
        // 等待所有的任务完成
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + " reach barrier");
        executorService.shutdown();
    }
}

