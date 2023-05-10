package org.scenarios.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServerMain {

    public static String host = "localhost";

    public static int backendServerPort = 8100;

    public static void main(String[] args) throws Exception {
        System.out.println("Starting the Test Server main!");

        String content = readFile("<path to 1MB.json >");

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate200 backend");
        SSLServerSendImediate200 sslServerSendImediate200 = new SSLServerSendImediate200();
        sslServerSendImediate200.run(backendServerPort, content);
        Thread.sleep(2000000);
        sslServerSendImediate200.shutdownServer();
        System.out.println(" <<<<<<<<<<<<<<<<<<<<<<<<<<< End sslServerSendImediate200 backend");

    }
    public static String readFile(String fileLocation) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileLocation));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            return everything;
        } finally {
            br.close();
        }
    }
}
