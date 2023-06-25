package org.scenarios.tests;

import org.scenarios.client.AbstractSSLClient;
import org.scenarios.client.NonBlockingClientSendContentLessThanContentLength;
import org.scenarios.client.SimpleNonBlockingClient;
import org.scenarios.client.helpers.RequestMethods;
import org.scenarios.server.*;
import java.io.*;

public class ScenarioTests {
    public static void main(String[] args) throws Exception, InterruptedException {
        // Backend server configs
        int backendServerPort=8100;
        String serverHost =  "localhost";
        // Client configs
        int serverPort = 8243;
        String Bearer = "< Authorization Bearer token for secured API >";

        String Content1MB = readFile("< absolute path >/1MB.json");
        String Content2KB = readFile("< absolute path >2KB.json");
        AbstractSSLServer server;

////////// Client send the full request content

        // SSL Server Send Immediate Response with Payload Test cases 1 to 8

        //SSLServerSendImediate503
        server = StartServer(new SSLServerSendImediate503(), Content1MB, backendServerPort );
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate200
        server = StartServer(new SSLServerSendImediate200(), Content1MB, backendServerPort );
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate300
        server = StartServer(new SSLServerSendImediate300(), Content1MB, backendServerPort );
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate400
        server = StartServer(new SSLServerSendImediate400(), Content1MB, backendServerPort );
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        // SSL Server Send Immediate Response Without Payload Test cases 8 to ....

        //SSLServerSendImediate503WithoutPayload
        server = StartServer(new SSLServerSendImediate503WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate200WithoutPayload
        server = StartServer(new SSLServerSendImediate200WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate300WithoutPayload
        server = StartServer(new SSLServerSendImediate300WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate400WithoutPayload
        server = StartServer(new SSLServerSendImediate400WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();


////////// Client send only the request content partially and not sending rest of the content, source connection will time out. Ex: Send content less than the content length.

        // SSL Server Send Immediate Response with Payload Test cases 1 to 16

        //SSLServerSendImediate503
        server = StartServer(new SSLServerSendImediate503(), Content1MB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();
        server = StartServer(new SSLServerSendImediate503(), Content2KB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate200
        server = StartServer(new SSLServerSendImediate200(), Content1MB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();
        server = StartServer(new SSLServerSendImediate200(), Content2KB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate300
        server = StartServer(new SSLServerSendImediate300(), Content1MB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();
        server = StartServer(new SSLServerSendImediate300(), Content2KB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate400
        server = StartServer(new SSLServerSendImediate400(), Content1MB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();
        server = StartServer(new SSLServerSendImediate400(), Content2KB, backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        // SSL Server Send Immediate Response Without Payload Test cases 17 to 24

        //SSLServerSendImediate503WithoutPayload
        server = StartServer(new SSLServerSendImediate503WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate200WithoutPayload
        server = StartServer(new SSLServerSendImediate200WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate300WithoutPayload
        server = StartServer(new SSLServerSendImediate300WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

        //SSLServerSendImediate400WithoutPayload
        server = StartServer(new SSLServerSendImediate400WithoutPayload(), "", backendServerPort );
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
        server.shutdownServer();

    }
    private static void StartClient(AbstractSSLClient client, String payload, RequestMethods method ) {
        client.run(payload, method );
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
    public static AbstractSSLServer StartServer(AbstractSSLServer server, String responseContent, int port) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(" >>>>> Start " + server.getClass().getSimpleName() + " backend with response content length : "+ responseContent.getBytes().length);
                server.run(port, responseContent);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        // Giving grace period to start the server
        Thread.sleep(500);
        return server;
    }
}
