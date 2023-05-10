package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;
import org.scenarios.client.helpers.TestPayloads;

public class ClientMain {

    public static String keyStoreLocation = "<keystore location>";
    public static String keyStorePassword = "<keystore password>";

    public static void main(String[] args) {
        System.out.println("Starting the Test Client main!");

        ClientMain main = new ClientMain();

        main.runHTTPClientsWithPayload("localhost", 8243, TestPayloads.LARGE_PAYLOAD_25K, RequestMethods.GET);
    }

    public void runHTTPClientsWithPayload(String host, int port, String payload, RequestMethods method) {

        System.out.println("Run ClientSendContentLessThanContentLength");
        ClientSendContentLessThanContentLength client1 = new ClientSendContentLessThanContentLength(host, port);
        client1.run(payload, method);
        System.out.println("Stop ClientSendContentLessThanContentLength");

    }
}