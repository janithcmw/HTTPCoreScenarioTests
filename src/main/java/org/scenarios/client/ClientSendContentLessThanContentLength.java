package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;

import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class ClientSendContentLessThanContentLength {
    private String host;
    private int port;
    public static final String CRLF = "\r\n";

    public ClientSendContentLessThanContentLength(String host, int port) {

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

                System.out.println("Client " + this.getClass().getName().toString() + " started");
                new ClientSendContentLessThanContentLength.ClientThread(sslSocket, payload, method).start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    static class ClientThread extends Thread {

        private SSLSocket sslSocket = null;
            private String payload;
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

                    // Start handling application content
                    InputStream inputStream = sslSocket.getInputStream();
                    OutputStream outputStream = sslSocket.getOutputStream();

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    PrintStream printWriter = new PrintStream(outputStream);

                    // Write data
                    printWriter.print(method + " /test/1 HTTP/1.1\r\n ");
                    printWriter.print("Accept: application/json\r\n");
                    printWriter.print("Connection: keep-alive\r\n");
                    printWriter.print("Authorization: Bearer eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6Ik1iX09sU1NYSDV6cWZmR1BRVDhLc29SX1VfWWEiLCJuYmYiOjE2ODM2NTc3OTQsImF6cCI6Ik1iX09sU1NYSDV6cWZmR1BRVDhLc29SX1VfWWEiLCJzY29wZSI6ImRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE2ODM2NjEzOTQsImlhdCI6MTY4MzY1Nzc5NCwianRpIjoiNWUzODIxMjEtODMzNS00YjY4LWEwOTAtMWYyYWQ4MjE0M2E4In0.OG0zwy9-qcagqdRHfcbaayOsmNc6osQk51fQpdTO-JV61UQWIGiIXJ-HU8dXhsr0BVJ2eYrzBuK3z4Gze4Sm_Hn3Zbpptfc6Ht9WEImHOGSY_S9wG6cSkYiMphwZtsMkix6n2W8YWwyUKRubIQXou-cruD-k_iowr7jf6XPxCN0_OkavC--aEA3Fjq_jHO3fGrBvFNFZ6hQn2mqpXfNO--MOIjG-4wffd3X5_aXVoZTLt7dvy9UJXmw_4Yo-vjIiDq2AQ_JYqsN-Xvz6BM1yHIWU5X6NbrXX_op54LfBj7xgcVhhMxHOf61A3FVukqo4nspY5BkVmOb32EzUxYRbAA\r\n");
                    printWriter.print("Content-Type: application/json\r\n");
                    printWriter.print("content-length: 1048576\r\n");
                    printWriter.print("\r\n");
                    printWriter.print(payload);
                    // Remove the eol
                    //printWriter.print("\r\n");
                    printWriter.flush();
                    Thread.sleep(650000);
                    printWriter.print("\r\n");

                    String line = null;
                    int i = 0;
                    while((line = bufferedReader.readLine()) != null){
                        System.out.println("Inut : "+line);
                        i++;
                    }
                    sslSocket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
