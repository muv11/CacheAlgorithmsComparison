package com.muv.generator;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class RequestsGenerator {

    String filename;

    public RequestsGenerator(String filename) {
        this.filename = filename;
    }

    public void writeRequests(int N, int M, int R) throws IOException {
        int min = 1;
        PrintWriter printWriter = new PrintWriter(filename, StandardCharsets.UTF_8);
        printWriter.print(N);
        printWriter.println(M);
        for (int i = 0; i < M; i++) {
            printWriter.println((int) (Math.random() * (R - min)) + min);
        }
        printWriter.close();
    }

}
