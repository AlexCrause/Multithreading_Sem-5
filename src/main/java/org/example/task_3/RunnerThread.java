package org.example.task_3;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RunnerThread implements Runnable{

    private String name;
    private CountDownLatch readySignal;
    private CountDownLatch startSignal;
    private CountDownLatch endSignal;

    public RunnerThread(String name, CountDownLatch readySignal, CountDownLatch startSignal, CountDownLatch endSignal) {
        this.name = name;
        this.readySignal = readySignal;
        this.startSignal = startSignal;
        this.endSignal = endSignal;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(1000, 3000));
            System.out.println("Участник: " + this.name + " на старте!");
            readySignal.countDown();
            startSignal.await();
            System.out.println("Участник: " + this.name + " побежал!");
            Thread.sleep(new Random().nextInt(3000, 5000));
            System.out.println("Участник: " + this.name + " финишировал!");
            endSignal.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
