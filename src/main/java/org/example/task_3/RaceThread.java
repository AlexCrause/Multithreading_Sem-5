package org.example.task_3;

import java.util.concurrent.CountDownLatch;

public class RaceThread implements Runnable{

    private CountDownLatch readySignal;
    private CountDownLatch startSignal;
    private CountDownLatch endSignal;

    public RaceThread(CountDownLatch readySignal, CountDownLatch startSignal, CountDownLatch endSignal) {
        this.readySignal = readySignal;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {
        try {
            readySignal.await();
            System.out.println("На старт!");
            Thread.sleep(100);
            System.out.println("Внимание!!");
            Thread.sleep(100);
            System.out.println("МАРШ!!!");
            startSignal.countDown();
            endSignal.await();
            System.out.println("Выполнено!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
