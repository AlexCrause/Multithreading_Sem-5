package org.example.task_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * В рамках выполнения задачи необходимо:
 * 3 бегуна должны прийти к старту гонки
 * Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
 * Программа должна отсчитать “На старт”, “Внимание”, “Марш”
 * Программа должна завершиться когда все участники закончат гонку.
 * Подумайте об использовании примитива синхронизации
 */
public class Program2 {

    public static final int COUNT_OF_RUNNERS = 3;

    public static void main(String[] args) {
        CountDownLatch readySignal = new CountDownLatch(COUNT_OF_RUNNERS);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(COUNT_OF_RUNNERS);

        List<RunnerThread> runners = new ArrayList<>(Arrays.asList(
                new RunnerThread("Вася", readySignal, startSignal, endSignal),
                new RunnerThread("Петя", readySignal, startSignal, endSignal),
                new RunnerThread("Коля", readySignal, startSignal, endSignal)
        ));
        new Thread(new RaceThread(readySignal, startSignal, endSignal)).start();
        for (RunnerThread runner : runners) {
            new Thread(runner).start();
        }
    }
}
