package com.koushik.imageprocessing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MultiThreadProcess {
    public void recolorMultithreadedUsingFuture(BufferedImage originalImage, BufferedImage resultImage, ExecutorService executors, int numberOfThreads) {

        List<Future<String>> tasks = new ArrayList<>();
        int width = originalImage.getWidth();
        int height = originalImage.getHeight() / numberOfThreads;
        ImageProcessingUtil imageProcessingUtil = new ImageProcessingUtil();
        for(int i = 0; i < numberOfThreads ; i++) {
            final int threadMultiplier = i;

            Future<String> futureTask = executors.submit(() -> {
                int xOrigin = 0;
                int yOrigin = height * threadMultiplier;
                imageProcessingUtil.recolorImage(originalImage, resultImage, xOrigin, yOrigin, width, height);
                return Thread.currentThread().getName();
            });
            tasks.add(futureTask);
        }

        for(Future<String> futureTask:tasks) {
            try {
                System.out.println(futureTask.get()+" is executed");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
    public void recolorMultithreadedUsingThread(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {

        List<Thread> threads = new ArrayList<>();
        int width = originalImage.getWidth();
        int height = originalImage.getHeight() / numberOfThreads;
        ImageProcessingUtil imageProcessingUtil = new ImageProcessingUtil();
        for(int i = 0; i < numberOfThreads ; i++) {
            final int threadMultiplier = i;
            Thread thread = new Thread(() -> {
                int xOrigin = 0 ;
                int yOrigin = height * threadMultiplier;
                imageProcessingUtil.recolorImage(originalImage, resultImage, xOrigin, yOrigin, width, height);
            });

            threads.add(thread);
        }

        for(Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
