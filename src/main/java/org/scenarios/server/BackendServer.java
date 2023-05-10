package org.scenarios.server;

import java.io.IOException;
import java.net.ServerSocket;

public class BackendServer {

    public static String ServerkeyStoreLocation = "<keystore location>";
    public static String ServerkeyStorePassword = "<keystore password>";
    protected ServerSocket ss;
    public static final String CRLF = "\r\n";
    public BackendServer() {
    }

    public void run(int port, String content) throws Exception {}

    public void shutdownServer() {
        try {
            System.out.println("Shutting down the server");
            ss.close();
        } catch (IOException ignore) {
            //
        }
    }
}