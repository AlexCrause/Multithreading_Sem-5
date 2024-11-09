package org.example.task_2;

public class ThreadB implements Runnable{

    private int counter = 100;
    private final ThreadA threadA;

    public ThreadB(ThreadA threadA) {
        this.threadA = threadA;
    }

    @Override
    public void run() {
        while (counter >= 0) {
            if (threadA.isSwitcherTask2()) {
                try {
                    Thread.sleep(100);
                    System.out.println(counter--);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
