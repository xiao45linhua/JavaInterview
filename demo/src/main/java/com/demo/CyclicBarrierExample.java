package com.demo;

/**
 * @author linhua
 * @apiNote
 * @date 2020-03-25 10:42 上午
 */

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    private ExecutorService executorService;
    private CyclicBarrier cyclicBarrier;
    private int parties;

    public CyclicBarrierExample(int parties) {
        executorService = Executors.newFixedThreadPool(parties);
        cyclicBarrier = new CyclicBarrier(parties, () -> System.out.println(Thread.currentThread().getName() + " gets barrierCommand done"));
        this.parties = parties;
    }

    public static void main(String[] args) {
        CyclicBarrierExample cyclicBarrierExample = new CyclicBarrierExample(10);
        cyclicBarrierExample.example();
    }

    public void example() {
        for (int i = 0; i < parties; i++) {
            executorService.submit(() -> {
                try {
                    Thread.sleep(1000);
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " gets job done");
            });
        }
        executorService.shutdown();
    }
}

