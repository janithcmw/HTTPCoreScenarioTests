package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;
import javax.net.ssl.*;
import java.io.*;
import java.net.SocketException;

public class NonBlockingClientSendContentLessThanContentLength extends AbstractSSLClient {
    private final String Bearer;
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
                    int contentLength = payload.getBytes().length;
                    // There is no possible partial client scenario with GET method or zero payload size.
                    if(contentLength==0 | method == RequestMethods.GET){
                        System.out.println("Actual Content-Length: is: "+ contentLength +" but "+ method +" method so not sending Content-Length header");
                        printWriter.print("\r\n");
                        printWriter.flush();
                        sslSocket.close();
                        return;
                    }
                    System.out.println("Actual Content-Length is "+ contentLength +" but sending Content-Length " + contentLength + 100);
                    // Sending large Content-Length to make the client sending partial content
                    printWriter.print("Content-Length: "+contentLength + 100+"\r\n");
                    printWriter.print("Content-Type: application/json\r\n");
                    printWriter.print("\r\n");
                    printWriter.print(payload);
                    // Remove the eol to make the client sending partial content
                    //printWriter.print("\r\n");
                    printWriter.flush();
                    Thread.sleep(200000);
                    printWriter.print("\r\n");

                    sslSocket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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

            }catch (SocketException e){
                System.out.println("Socket Exception occurred with the error message : "+ e.getMessage());
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
