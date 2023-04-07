package com.koushik.imageprocessing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageProcessingMain {
    public static final String SOURCE_FILE = "src/main/resources/white-flowers.jpg";
    public static final String DESTINATION_FILE = "src/main/resources/white-flowers-modified.jpg";

    public static void main(String[] args) throws IOException {

        BufferedImage originalImage = ImageIO.read(new File(SOURCE_FILE));
        BufferedImage resultImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);

        long startTime = System.currentTimeMillis();
        /***
            Uncomment below line for the Single Threaded process
         ***/
//        recolorSingleThreaded(originalImage, resultImage);
        int numberOfThreads = 6;
        recolorMultiThreaded(originalImage, resultImage, numberOfThreads);
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;

        File outputFile = new File(DESTINATION_FILE);
        ImageIO.write(resultImage, "jpg", outputFile);

        System.out.println(duration);
        System.exit(0);
    }


    public static void recolorMultiThreaded(BufferedImage originalImage, BufferedImage resultImage, int numberOfThreads) {
        MultiThreadProcess multiThreadProcess = new MultiThreadProcess();
        ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads) ;
        //Using ExecutorService and Futures
        multiThreadProcess.recolorMultithreadedUsingFuture(originalImage, resultImage, executors,numberOfThreads);
        //Using Threads
//        multiThreadProcess.recolorMultithreadedUsingThread(originalImage, resultImage, numberOfThreads);
    }

    public static void recolorSingleThreaded(BufferedImage originalImage, BufferedImage resultImage) {
        ImageProcessingUtil imageProcessingUtil = new ImageProcessingUtil();
        imageProcessingUtil.recolorImage(originalImage, resultImage, 0, 0, originalImage.getWidth(), originalImage.getHeight());
    }

}

