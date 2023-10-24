package com.muv;

import com.muv.launcher.Launcher;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Launcher launcher = new Launcher();
        System.gc();
        launcher.launchFifoAlgorithm();
        System.gc();
        launcher.launchLfuAlgorithm();
        System.gc();
        launcher.launchLruAlgorithm();
        System.gc();
        launcher.launchTwoQueuesAlgorithm();
        System.gc();
        launcher.launchOptimalAlgorithm();

    }

}
