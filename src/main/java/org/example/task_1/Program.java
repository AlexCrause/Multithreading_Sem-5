package org.example.task_1;

/**
 * В рамках выполнения задачи необходимо:
 * Создать два класса: ObjectA, ObjectB
 * Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
 */
public class Program {
    static Object objectA = new Object();
    static Object objectB = new Object();
    public static void main(String[] args) {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                workTread1();
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                workTread2();
            }
        });
        threadA.start();
        threadB.start();
    }

    public static void workTread1(){
        synchronized (objectA){
            try {
                System.out.println("Поток 1 захватил объект A");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objectB) {
                System.out.println("Поток 1 захватил объект B");
            }
        }
    }

    public static void workTread2(){
        synchronized (objectB){
            System.out.println("Поток 2 захватил объект B");
            synchronized (objectA) {
                System.out.println("Поток 2 захватил объект A");
            }
        }
    }
}
