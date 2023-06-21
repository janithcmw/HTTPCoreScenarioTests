package org.scenarios.tests;

import org.scenarios.client.AbstractSSLClient;
import org.scenarios.client.NonBlockingClientSendContentLessThanContentLength;
import org.scenarios.client.SimpleNonBlockingClient;
import org.scenarios.client.helpers.RequestMethods;
import org.scenarios.server.AbstractSSLServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PassThroughMessageProcessorPoolTest {
    public static void main(String[] args) throws Exception, InterruptedException {
        // Backend server configs
        int backendServerPort = 8100;
        String serverHost = "localhost";
        // Client configs
        int serverPort = 8243;
        String Bearer = "< Authorization Bearer token for secured API >";
        String context ;


        String Content1MB = readFile("< absolute path >/1MB.json");
        String Content2KB = readFile("< absolute path >/2KB.json");
        String Content20KB = readFile("< absolute path >/20KB.json");

        // Test case description
        // Set the below properties in the deployment.toml before running the test case
        //[transport.http]
        //consume_and_discard = true
        //core_worker_pool_size = 2
        //max_worker_pool_size = 3
        //expected_max_queueing_time=10

        // 1 Source connection
        //
        // 1.1 socket timed out when the thread is in the queue
        // 1.2 socket timed out when the thread get blocked
        // 1.3 thread get blocked in the queue some time and released and served the request
        //
        // 2 Target connection
        //
        // 2.1 socket timed out when the thread is in the queue
        // 2.2 socket timed out when the thread get blocked
        // 2.3 thread get blocked in the queue some time and released and served the request


        // Starting MI as a backend server
        // and consume_and_discard = false in MI
        // deploy the  SendImmediate503responseAPI.xml API in wso2 mi server
        // deploy the  SendDelayResponseAPI.xml API in wso2 mi server
        // copy the class mediator class-mediator-1.0-SNAPSHOT.jar in to <MI>/lib
        // Create an API in APIM server with the context /test version 1 if not you can modify the context param
        // Add the backend as https://localhost:8253 ( MI server host and port )

        // Test 1.1
        context = "/test/1/SendImmediate503responseAPI?id=50KB";

        System.out.println("Test 1.1 ");
        // Sending two partial requests when pool size is two all the PT threads get blocked to consume the two request since the backend send the response immediately
        StartConcurrentClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, context, Bearer ), Content20KB, RequestMethods.POST);
        StartConcurrentClient( new NonBlockingClientSendContentLessThanContentLength(serverHost, serverPort, context, Bearer ), Content20KB, RequestMethods.POST);
        Thread.sleep(5000);
        // Sending another simple request after some time, and it will put in to the PT queue until a PT thread is available
        StartConcurrentClient( new SimpleNonBlockingClient(serverHost, serverPort,context, Bearer), Content20KB, RequestMethods.POST);
        // This request get processed when PT thread is available after the source connection timeout of the first two requests.
        Thread.sleep(600000);

        // Test 2.2
        context = "/test/1/SendDelayResponseAPI?delay=5000";

        System.out.println("Test 2.1 ");
        // Sending a simple request, and it will get the response after the configured delay value (?delay=20000)
        StartConcurrentClient( new SimpleNonBlockingClient(serverHost, serverPort,context, Bearer), Content2KB, RequestMethods.POST);
        Thread.sleep(1000);
        // add script thread sleep to put resource of the API to block the PT thread pool
        /*
            property name="delay" expression="$url:delay" />
            <script language="js">
                var delay = mc.getProperty('delay');
                java.lang.Thread.sleep(delay);
            </script>
        */
        // Sending two PUT requests when pool size is two all the PT threads get blocked to consume the two request since the backend send the response immediately
        context = "/test/1/SendDelayResponseAPI?delay=1000";
        //StartConcurrentClient( new SimpleNonBlockingClient(serverHost, serverPort,context, Bearer), Content2KB, RequestMethods.POST);
        //StartConcurrentClient( new SimpleNonBlockingClient(serverHost, serverPort,context, Bearer), Content2KB, RequestMethods.PUT);
        //Thread.sleep(600000);

    }
    private static void StartClient(AbstractSSLClient client, String payload, RequestMethods method ) {
        client.run(payload, method );
    }
    private static void StartConcurrentClient(AbstractSSLClient client, String payload, RequestMethods method ) {
        Thread thread = new Thread(() -> {
            try {
                System.out.println(" >>>>> Start " + client.getClass().getSimpleName() + " client with request content length : "+ payload.getBytes().length);
                client.run(payload, method );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
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
