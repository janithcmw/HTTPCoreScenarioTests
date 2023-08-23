package org.scenarios.server;

import java.io.IOException;
import java.net.ServerSocket;

public class AbstractSSLServer {

    public static String ServerkeyStoreLocation = "<keystore location>";
    public static String ServerkeyStorePassword = "wso2carbon";
    public ServerSocket ss;
    public static final String CRLF = "\r\n";
    public AbstractSSLServer() {
        System.out.println("initiating "+ this.getClass().getSimpleName() +" server ");
    }
    public boolean isserverdone = true;

    public void run(int port, String content) throws Exception {}

    public void shutdownServer() throws InterruptedException {
        try {
            while (!isserverdone){
                Thread.sleep(10);
            }
            System.out.println("Shutting down the "+ this.getClass().getSimpleName() +" server");
            ss.close();
        } catch (IOException e) {
            System.out.println("Error while shutting down the server ");
        }
    }
    public static void setServerkeyStoreLocation(String baseLocation) {
        ServerkeyStoreLocation = baseLocation + "/serverKeyStoreLocation/wso2carbon.jks";
    }
}