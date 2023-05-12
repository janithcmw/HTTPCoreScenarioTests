package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;
import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class NonBlockingClientSendContentLessThanContentLength {
    private static String Bearer;
    private final String host;
    private final int port;

    public NonBlockingClientSendContentLessThanContentLength(String host, int port, String Bearer) {
        this.Bearer = Bearer;
        this.host = host;
        this.port = port;
    }

    public void run(String payload, RequestMethods method) {
        try {
            // Create ssl socket
            SSLContext sslContext = this.createSSLContext();
            try {
                // Create socket factory
                SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                // Create socket
                SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(this.host, this.port);
                System.out.println("Client " + this.getClass().getName() + " started");
                new NonBlockingClientSendContentLessThanContentLength.ClientThread(sslSocket, payload, method).start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    static class ClientThread extends Thread {

            private final SSLSocket sslSocket;
            private final String payload;
            RequestMethods method;

            ClientThread(SSLSocket sslSocket, String payload, RequestMethods method) {
                this.sslSocket = sslSocket;
                this.payload = payload;
                this.method = method;
            }

            public void run() {
                sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
                try {
                    // Start handshake
                    sslSocket.startHandshake();
                    // Get session after the connection is established
                    SSLSession sslSession = sslSocket.getSession();
                    System.out.println("SSLSession :");
                    System.out.println("\tProtocol : " + sslSession.getProtocol());
                    System.out.println("\tCipher suite : " + sslSession.getCipherSuite());
                    System.out.println("Connection established with the backend");
                    // Start handling application content
                    OutputStream outputStream = sslSocket.getOutputStream();
                    // Create a thread to read the response
                    Thread responseThread = new Thread(new ResponseReader(sslSocket));
                    responseThread.start();

                    PrintStream printWriter = new PrintStream(outputStream);

                    // Write data
                    printWriter.print(method + " /test/1 HTTP/1.1\r\n ");
                    printWriter.print("Accept: application/json\r\n");
                    printWriter.print("Connection: keep-alive\r\n");
                    printWriter.print("Authorization: Bearer "+ Bearer +"\r\n");
                    printWriter.print("Content-Type: application/json\r\n");
                    int contentLength = payload.getBytes().length;
                    System.out.printf("Actual Content-Length: is: "+ contentLength +"but sending large Content-Length: " + contentLength + 100);
                    // Sending large Content-Length to make the client sending partial content
                    printWriter.print("Content-Length: "+contentLength + 100+"\r\n");
                    printWriter.print("\r\n");
                    printWriter.print(payload);
                    // Remove the eol to make the client sending partial content
                    //printWriter.print("\r\n");
                    printWriter.flush();
                    Thread.sleep(650000);
                    printWriter.print("\r\n");

                    sslSocket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        private class ResponseReader implements Runnable {
            private final SSLSocket sslSocket;
            public ResponseReader(SSLSocket sslSocket) {
                this.sslSocket = sslSocket;
            }
            @Override
            public void run() {
                try {
                    System.out.println("Reading the response ...");
                    InputStream inputStream = sslSocket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    String line;
                    while((line = bufferedReader.readLine()) != null){
                        System.out.println("Response : "+line);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private SSLContext createSSLContext() {

        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(ClientMain.keyStoreLocation), ClientMain.keyStorePassword.toCharArray());

            // Create key manager
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "wso2carbon".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            // Create trust manager
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            // Initialize SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(km, tm, null);

            return sslContext;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
