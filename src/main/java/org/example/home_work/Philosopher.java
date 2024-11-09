package org.example.home_work;

import java.util.concurrent.locks.Lock;

class Philosopher extends Thread {
    private final String name;
    private final Lock leftFork;
    private final Lock rightFork;
    private int mealsEaten = 0;

    public Philosopher(String name, Lock leftFork, Lock rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                think();
                eat();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void eat() {
        leftFork.lock();
        try {
            rightFork.lock();
            try {
                System.out.println(name + " начал есть.");
                Thread.sleep((long) (Math.random() * 1000));
                mealsEaten++;
                System.out.println(name + " закончил есть. Всего принял пищу: " + mealsEaten);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                rightFork.unlock();
            }
        } finally {
            leftFork.unlock();
        }
    }

    public void think() throws InterruptedException {
        System.out.println(name + " размышляет.");
        Thread.sleep((long) (Math.random() * 1000));
    }
}

