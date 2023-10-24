package com.muv.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FileWorker {

    public int N;
    public int M;
    public Map<Long, TreeSet<Integer>> requestToIndexes = new HashMap<>();

    public ArrayList<Long> readData(String fileName) {
        ArrayList<Long> idRequests = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String[] size = bufferedReader.readLine().split(" ");
            N = Integer.parseInt(size[0]);
            M = Integer.parseInt(size[1]);
            for (int i = 0; i < M; i++) {
                long request = Long.parseLong(bufferedReader.readLine());
                idRequests.add(request);
                if (requestToIndexes.containsKey(request)) {
                    requestToIndexes.get(request).add(i);
                } else {
                    TreeSet<Integer> indexSet = new TreeSet<>();
                    indexSet.add(i);
                    requestToIndexes.put(request, indexSet);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return idRequests;
    }

    public void writeResults(String algoName, String fileName, int counter, float time, long memory) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter("results " + algoName + ".txt", true));
        printWriter.println(fileName + " | " + counter + " | " + time + " | " + memory);
        printWriter.close();
    }

}
