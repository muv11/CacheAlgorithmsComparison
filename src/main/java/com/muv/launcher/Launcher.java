package com.muv.launcher;

import com.muv.algorithm.*;
import com.muv.io.FileWorker;

import java.io.IOException;
import java.util.ArrayList;

public class Launcher {

    private final ArrayList<String> files;
    private final FileWorker fileWorker = new FileWorker();

    public Launcher() {
        files = new ArrayList<>();
        files.add("src/main/resources/10N50M60R.txt");
        files.add("src/main/resources/100N200M300R.txt");
        files.add("src/main/resources/1000N2500M2800R.txt");
        files.add("src/main/resources/1000N7000M7100R.txt");
        files.add("src/main/resources/1000N7000M10000R.txt");
        files.add("src/main/resources/5000N10000M15000R.txt");
        files.add("src/main/resources/10000N25000M20000R.txt");
        files.add("src/main/resources/10000N25000M27000R.txt");
        files.add("src/main/resources/10000N50000M11000R.txt");
        files.add("src/main/resources/10000N50000M15000R.txt");
        files.add("src/main/resources/10000N50000M27000R.txt");
        files.add("src/main/resources/10000N100000M11000R.txt");
        files.add("src/main/resources/10000N100000M15000R.txt");
        files.add("src/main/resources/20000N100000M15000R.txt");
        files.add("src/main/resources/20000N100000M30000R.txt");
    }

    public void launchFifoAlgorithm() throws IOException {
        for (String file : files) {
            ArrayList<Long> idRequests = fileWorker.readData(file);

            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long startTime = System.nanoTime();

            FifoAlgorithm fifoAlgorithm = new FifoAlgorithm(fileWorker.N);
            int counter = fifoAlgorithm.count(idRequests);

            float totalTime = (System.nanoTime() - startTime) / 1000000000f;
            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long totalMemory = (afterUsedMemory - beforeUsedMemory) / 1024;

            fileWorker.writeResults("FIFO", file, counter, totalTime, totalMemory);
        }
    }

    public void launchLfuAlgorithm() throws IOException {
        for (String file : files) {
            ArrayList<Long> idRequests = fileWorker.readData(file);

            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long startTime = System.nanoTime();

            LfuAlgorithm lfuAlgorithm = new LfuAlgorithm(fileWorker.N);
            int counter = lfuAlgorithm.count(idRequests);

            float totalTime = (System.nanoTime() - startTime) / 1000000000f;
            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long totalMemory = (afterUsedMemory - beforeUsedMemory) / 1024;

            fileWorker.writeResults("LFU", file, counter, totalTime, totalMemory);
        }
    }

    public void launchLruAlgorithm() throws IOException {
        for (String file : files) {
            ArrayList<Long> idRequests = fileWorker.readData(file);

            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long startTime = System.nanoTime();

            LruAlgorithm lruAlgorithm = new LruAlgorithm(fileWorker.N);
            int counter = lruAlgorithm.count(idRequests);

            float totalTime = (System.nanoTime() - startTime) / 1000000000f;
            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long totalMemory = (afterUsedMemory - beforeUsedMemory) / 1024;

            fileWorker.writeResults("LRU", file, counter, totalTime, totalMemory);
        }
    }

    public void launchTwoQueuesAlgorithm() throws IOException {
        for (String file : files) {
            ArrayList<Long> idRequests = fileWorker.readData(file);

            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long startTime = System.nanoTime();

            TwoQueuesAlgorithm twoQueuesAlgorithm = new TwoQueuesAlgorithm(fileWorker.N);
            int counter = twoQueuesAlgorithm.count(idRequests);

            float totalTime = (System.nanoTime() - startTime) / 1000000000f;
            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long totalMemory = (afterUsedMemory - beforeUsedMemory) / 1024;

            fileWorker.writeResults("Two Queues", file, counter, totalTime, totalMemory);
        }
    }

    public void launchOptimalAlgorithm() throws IOException {
        for (String file : files) {
            ArrayList<Long> idRequests = fileWorker.readData(file);

            System.gc();
            long beforeUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long startTime = System.nanoTime();

            OptimalAlgorithm optimalAlgorithm = new OptimalAlgorithm(fileWorker.N, fileWorker.M, fileWorker.requestToIndexes);
            int counter = optimalAlgorithm.count(idRequests);

            float totalTime = (System.nanoTime() - startTime) / 1000000000f;
            long afterUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            long totalMemory = (afterUsedMemory - beforeUsedMemory) / 1024;

            fileWorker.writeResults("Optimal", file, counter, totalTime, totalMemory);
        }
    }

}
