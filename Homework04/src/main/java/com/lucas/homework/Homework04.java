package com.lucas.homework;

import java.util.HashMap;
import java.util.concurrent.*;

public class Homework04 {
    public static int globalResult = 0;
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 1. global variable
        Thread thread = new Thread(new Runnable() {
            public void run() {
                globalResult = sum();
            }
        });
        thread.start();
        thread.join();
        System.out.println(globalResult);

        // 2. thread pool
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Callable() {
            public Object call() {
                return sum();
            }
        });
        System.out.println(future.get());

        // 3. custom thread
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        Thread customThread = new CustomThread(result);
        customThread.start();
        customThread.join();
        System.out.println(result.get("result"));
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }

    static class CustomThread extends Thread {
        public HashMap<String, Integer> result;

        public CustomThread(HashMap<String, Integer> map) {
            result = map;
        }

        public void run() {
            result.put("result", sum());
        }
    }
}
