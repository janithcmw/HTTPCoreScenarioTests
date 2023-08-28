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
import java.util.Scanner;

import static org.scenarios.client.AbstractSSLClient.setKeyStoreLocation;
import static org.scenarios.server.AbstractSSLServer.setServerkeyStoreLocation;


public class ScenarioTests {
    static String Bearer;
    static String context;
    public static void main(String[] args) throws Exception, InterruptedException {
//        String basePath = pathFinder();
        String basePath = "/Users/janithw/Workspace/WSO2Products/apim/Tickets/BNY-290-test/runJAr";
        setKeyStoreLocation(basePath);
        setServerkeyStoreLocation(basePath);
        readBearerAndContext();
        // Backend server configs
        int backendServerPort=8100;
        String serverHost =  "localhost";
        // Client configs
        int serverPort = 8243;
        String Content1MB = readFile(basePath + "/resources/1MB.json");
        String Content2KB = readFile(basePath + "/resources/2KB.json");
        AbstractSSLServer server;

////////// Client send the full request content

        // SSL Server Send Immediate Response with Payload Test cases 1 to 8

        //SSLServerSendImediate503
//        server = StartServer(new SSLServerSendImediate503(), Content1MB, backendServerPort );
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();

        //SSLServerSendImediate200
//        server = StartServer(new SSLServerSendImediate200(), Content1MB, backendServerPort );
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();

//        server = StartServer(new SSLServerSendImediate200(), Content1MB, backendServerPort );
//        Thread.sleep(1000);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate300
//        server = StartServer(new SSLServerSendImediate300(), Content1MB, backendServerPort );
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate400
//        server = StartServer(new SSLServerSendImediate400(), Content1MB, backendServerPort );
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new SimpleNonBlockingClient(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        // SSL Server Send Immediate Response Without Payload Test cases 8 to ....
//
//        //SSLServerSendImediate503WithoutPayload
        // Will be slowed down becase there is a thread sleep in the system untill the sockent time out of the APIM server
//        server = StartServer(new SSLServerSendImediate503WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate200WithoutPayload
//        server = StartServer(new SSLServerSendImediate200WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate300WithoutPayload
//        server = StartServer(new SSLServerSendImediate300WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate400WithoutPayload
//        server = StartServer(new SSLServerSendImediate400WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//
//////////// Client send only the request content partially and not sending rest of the content, source connection will time out. Ex: Send content less than the content length.
//
//        // SSL Server Send Immediate Response with Payload Test cases 1 to 16
//
//        //SSLServerSendImediate503
//        server = StartServer(new SSLServerSendImediate503(), Content1MB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//        server = StartServer(new SSLServerSendImediate503(), Content2KB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate200
//        server = StartServer(new SSLServerSendImediate200(), Content1MB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//        server = StartServer(new SSLServerSendImediate200(), Content2KB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate300
//        server = StartServer(new SSLServerSendImediate300(), Content1MB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//        server = StartServer(new SSLServerSendImediate300(), Content2KB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate400
//        server = StartServer(new SSLServerSendImediate400(), Content1MB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//        server = StartServer(new SSLServerSendImediate400(), Content2KB, backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        // SSL Server Send Immediate Response Without Payload Test cases 17 to 24
//
//        //SSLServerSendImediate503WithoutPayload
//        server = StartServer(new SSLServerSendImediate503WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate200WithoutPayload
//        server = StartServer(new SSLServerSendImediate200WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate300WithoutPayload
//        server = StartServer(new SSLServerSendImediate300WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();
//
//        //SSLServerSendImediate400WithoutPayload
//        server = StartServer(new SSLServerSendImediate400WithoutPayload(), "", backendServerPort );
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content1MB, RequestMethods.POST);
//        StartClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, Bearer), Content2KB, RequestMethods.POST);
//        server.shutdownServer();

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
            decodedPath = URLDecoder.decode(path, "UTF-8").replace("ScenarioTests-jar-with-dependencies.jar", "");
        } catch (UnsupportedEncodingException e) {
            System.out.println("An Exception when encoding the file path URI");
        }
        System.out.println("Reading configuration file form the path, " + decodedPath);
        return decodedPath;
    }

    public static String readBearer () {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Bearer Token: ");
        String userInput = scanner.nextLine();

        return userInput;
    }

    public static void readBearerAndContext() {

        String bearer = "eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbiIsImF1dCI6IkFQUExJQ0FUSU9OIiwiYXVkIjoiNm1DeW9GQ29EZ0JWOFpEaDdiNWdWZU93enIwYSIsIm5iZiI6MTY5MjgxMzAyMCwiYXpwIjoiNm1DeW9GQ29EZ0JWOFpEaDdiNWdWZU93enIwYSIsInNjb3BlIjoiZGVmYXVsdCIsImlzcyI6Imh0dHBzOlwvXC9sb2NhbGhvc3Q6OTQ0M1wvb2F1dGgyXC90b2tlbiIsImV4cCI6MTY5MjgxNjYyMCwiaWF0IjoxNjkyODEzMDIwLCJqdGkiOiJkMjcyNDY4Zi05Y2FjLTQ4NTItYTA1Yi0yZDEzNDQwMDNkZTcifQ.qGKX4d1Fdt9_yxkteDsR3rtbfkLDL1EEGWXT5iYtqptPNJzy2e--T54Vb8ZUlOIXA6XyjvkRlp3SomJhv5R7A_6hu0wyb_CWgHMkhk-R-D-4l_1nbPtcsHqnYkfgCxG74WsKMK1gCiCdUPNToYxscEhNjPys7hIfNgrKtmd9URbVJzwbg3rcS30VIpvTX8KSBPnZ0USE9cx6vb-9m51m8gnu7Pm8BFrIIczbUn-uTKNNuOsjqa2KJxLBU21X-LJYqlFmm8n_gL4fZjJ-j2LXYlWvdrofLCvN9cRFIaYoibJlTrUsBxYKTSBF--ZmPJfExlwGNdFkvQ782UAQ9xYb1w";
        String apiBaseContext = "/test/1";
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your Bearer Token: ");
        String userInputBearer = scanner.nextLine();
        if(!userInputBearer.isBlank() || !userInputBearer.isEmpty()) {
            bearer = userInputBearer;
        }
        System.out.print("Enter The API Context: ");
        String userInputContext = scanner.nextLine();
        if(!userInputContext.isBlank() || !userInputContext.isEmpty()) {
            apiBaseContext = userInputContext;
        }
        Bearer = bearer;
        context = apiBaseContext;
    }
}
