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

        // This class contains main methods to run the back end servers separately

        ////////////// Backend server list SSLServerSendImediatexxx /////////////

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate503 backend");
        SSLServerSendImediate503 sslServerSendImediate503 = new SSLServerSendImediate503();
        sslServerSendImediate503.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate200 backend");
        SSLServerSendImediate200 sslServerSendImediate200 = new SSLServerSendImediate200();
        sslServerSendImediate200.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate300 backend");
        SSLServerSendImediate300 sslServerSendImediate300 = new SSLServerSendImediate300();
        sslServerSendImediate300.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start sslServerSendImediate400 backend");
        SSLServerSendImediate400 sslServerSendImediate400 = new SSLServerSendImediate400();
        sslServerSendImediate400.run(backendServerPort, content);

        ///////////// Backend server list SSLServerSendImediatexxxWithoutPayload /////////////

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start SSLServerSendImediate503WithoutPayload backend");
        SSLServerSendImediate503WithoutPayload sslServerSendImediate503WithoutPayload= new SSLServerSendImediate503WithoutPayload();
        sslServerSendImediate503WithoutPayload.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start SSLServerSendImediate200WithoutPayload backend");
        SSLServerSendImediate200WithoutPayload sslServerSendImediate200WithoutPayload = new SSLServerSendImediate200WithoutPayload();
        sslServerSendImediate200WithoutPayload.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start SSLServerSendImediate300WithoutPayload backend");
        SSLServerSendImediate300WithoutPayload sslServerSendImediate300WithoutPayload = new SSLServerSendImediate300WithoutPayload();
        sslServerSendImediate300WithoutPayload.run(backendServerPort, content);

        System.out.println(" >>>>>>>>>>>>>>>>>>>>>>>>>>> Start SSLServerSendImediate400WithoutPayload backend");
        SSLServerSendImediate400WithoutPayload sslServerSendImediate400WithoutPayload = new SSLServerSendImediate400WithoutPayload();
        sslServerSendImediate400WithoutPayload.run(backendServerPort, content);

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
