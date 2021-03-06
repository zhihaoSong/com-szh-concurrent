package com.szh.algorithm.queue;

import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        final Semaphore semaphore = new Semaphore(1);
        final SynchronousQueue<String> queue = new SynchronousQueue<String>();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lock.lock();
                        //semaphore.acquire();
                        String input = queue.take();
                        String output = TestDo.doSome(input);
                        System.out.println(Thread.currentThread().getName() + ":" + output);
                        //semaphore.release();
                        lock.unlock();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("begin:" + (System.currentTimeMillis() / 1000));
        for (int i = 0; i < 10; i++) {
            String input = i + "";
            try {
                queue.put(input);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

class TestDo {
    public static String doSome(String input) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String output = input + ":" + (System.currentTimeMillis() / 1000);
        return output;
    }
}
