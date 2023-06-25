package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class SimpleHTTPSClient extends AbstractSSLClient{

    private final String host;
    private final int port;
    private final String context;
    private final String Bearer;

    public SimpleHTTPSClient(String host, int port, String context, String bearer) {
        this.host = host;
        this.port = port;
        this.context = context;
        Bearer = bearer;
    }

    public void run(String payload, RequestMethods method) {
        try {
            SSLContext sslContext = this.createSSLContext();
            HttpClient client = HttpClient.newBuilder().sslContext(sslContext).build();
            String url = "https://" + host + ":" + port + context;

            int contentLength = payload.getBytes().length;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .header("Authorization",  "Bearer " + Bearer)
                    .method( method.toString(), HttpRequest.BodyPublishers.ofString(payload, StandardCharsets.UTF_8))
                    .build();


            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Server Response headers = " +  response.headers().map());
            System.out.println("Server Response body = " +  response.body());

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
