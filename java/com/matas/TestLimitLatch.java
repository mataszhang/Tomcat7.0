package com.matas;

import org.apache.tomcat.util.threads.LimitLatch;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author matas
 * @date 2018/8/27 17:02
 * @email mataszhang@163.com
 */
public class TestLimitLatch {
    public static void main(String[] args) {
        LimitLatch limitLatch = new LimitLatch(2L); //最多同时只让2个线程运行

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executorService.submit(new Worker(limitLatch));
        }
    }

    static class Worker implements Runnable {
        private LimitLatch limitLatch;

        Worker(LimitLatch limitLatch) {
            this.limitLatch = limitLatch;
        }


        @Override
        public void run() {
            try {
                limitLatch.countUpOrAwait();
                System.out.println(Thread.currentThread().getName() + " running*****");
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5000)+1);
                System.out.println(Thread.currentThread().getName() + " end--");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                limitLatch.countDown();
            }
        }
    }

}
