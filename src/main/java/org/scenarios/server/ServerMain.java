package org.scenarios.server;

import org.scenarios.client.helpers.TestPayloads;

public class ServerMain {

    public static String host = "localhost";

    public static int backendServerPort = 8100;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting the Test Server main!");

        System.setProperty("javax.net.ssl.keyStore", "<keystore location>");
        System.setProperty("javax.net.ssl.keyStorePassword", "<keystore password>");

        ServerMain main = new ServerMain();

        String content = TestPayloads.LARGE_PAYLOAD_1MB;

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate200 backend");
        SSLServerSendImediate200 sslServerSendImediate200 = new SSLServerSendImediate200();
        sslServerSendImediate200.run(backendServerPort, content);
        Thread.sleep(2000000);
        sslServerSendImediate200.shutdownServer();
        System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<< End sslServerSendImediate200 backend");

    }
}
