package org.scenarios.tests;

import org.scenarios.client.AbstractSSLClient;
import org.scenarios.client.NonBlockingClientSendContentLessThanContentLength;
import org.scenarios.client.SimpleNonBlockingClient;
import org.scenarios.client.helpers.RequestMethods;
import org.scenarios.server.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.scenarios.client.AbstractSSLClient.setKeyStoreLocation;
import static org.scenarios.server.AbstractSSLServer.setServerkeyStoreLocation;

public class ScenarioTests {
    public static void main(String[] args) throws Exception, InterruptedException {
        String basePath = pathFinder();
        setKeyStoreLocation(basePath);
        setServerkeyStoreLocation(basePath);
        // Backend server configs
        int backendServerPort=8100;
        String serverHost =  "localhost";
        // Client configs
        int serverPort = 8243;
        String Bearer = "< Authorization Bearer token for secured API >";

        String Content1MB = readFile(basePath + "resources/1MB.json");
        String Content2KB = readFile(basePath + "resources/2KB.json");
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

    public static String pathFinder() {
        String path = ScenarioTests.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String decodedPath = null;
        try {
//            decodedPath = URLDecoder.decode(path, "UTF-8").replace("/target/classes", "");
//            decodedPath = URLDecoder.decode(path, "UTF-8");
//            log.info("the Picking up path is " + path);
            decodedPath = URLDecoder.decode(path, "UTF-8").replace("HTTPCoreScenarioTests-1.0-SNAPSHOT-jar-with-dependencies.jar", "");
        } catch (UnsupportedEncodingException e) {
            System.out.println("An Exception when encoding the file path URI");
        }
        System.out.println("Reading configuration file form the path, " + decodedPath);
        return decodedPath;
    }
}
