package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClientMain {

    public static String keyStoreLocation = "<keystore location>";
    public static String keyStorePassword = "<keystore password>";
    public static int serverPort = 8243;
    public static String Bearer = "eyJ4NXQiOiJNell4TW1Ga09HWXdNV0kwWldObU5EY3hOR1l3WW1NNFpUQTNNV0kyTkRBelpHUXpOR00wWkdSbE5qSmtPREZrWkRSaU9URmtNV0ZoTXpVMlpHVmxOZyIsImtpZCI6Ik16WXhNbUZrT0dZd01XSTBaV05tTkRjeE5HWXdZbU00WlRBM01XSTJOREF6WkdRek5HTTBaR1JsTmpKa09ERmtaRFJpT1RGa01XRmhNelUyWkdWbE5nX1JTMjU2IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhZG1pbkBjYXJib24uc3VwZXIiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6Ik1iX09sU1NYSDV6cWZmR1BRVDhLc29SX1VfWWEiLCJuYmYiOjE2ODM2OTY0MjcsImF6cCI6Ik1iX09sU1NYSDV6cWZmR1BRVDhLc29SX1VfWWEiLCJzY29wZSI6ImRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvbG9jYWxob3N0Ojk0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjkyMjMzNzIwMzY4NTQ3NzUsImlhdCI6MTY4MzY5NjQyNywianRpIjoiZDQ4Y2UxMWQtZGZkZS00Y2U2LThlODAtYTAwMzRiMmJhMzY5In0.s5R2TztUdSQWufvNlPjfT8jB_BPHl4XS4bbyUE5UyL-4GkW2BWkJNnyM7MhBGfqDXCIZ5k7qDfuGEYVzNq9RBLtOkjxqAR-npHHyhXvUD18gLcosHP76VMKj1tkbQqyPGtuq4ENJezxda9KYRTne8n995M7ypZiRByG-1OL4f3jzqacsDghzdK-sQ4BUF6KBj3GAhoaG0RfDntOgS4AMDOpAF1umuoVxPMz81KrHNJtRBvt8ld7x68cFhbY1cEpkf2RIH_nsQfH8A4mAC27GfIBTs3L40h5YEHGONz5w9mbg_ysChA0mduuYQVOkHw_-53VEdp5DRTT3C1P5Ee-I0g";


    public static void main(String[] args) throws IOException {
        System.out.println("Starting the Test Client main!");
        ClientMain main = new ClientMain();
        String content = readFile(" < Path to 1MB.json >");
        main.runHTTPClientsWithPayload("localhost", serverPort,content , RequestMethods.POST);
    }
    public void runHTTPClientsWithPayload(String host, int port, String payload, RequestMethods method) {

//        System.out.println("Run ClientSendContentLessThanContentLength");
//        ClientSendContentLessThanContentLength clientSendContentLessThanContentLength = new ClientSendContentLessThanContentLength(host, port, Bearer);
//        clientSendContentLessThanContentLength.run(payload, method);
//        System.out.println("Stop ClientSendContentLessThanContentLength");
//
//        System.out.println("Run SimpleBlockingClient");
//        SimpleBlockingClient simpleBlockingClient = new SimpleBlockingClient(host, port, Bearer);
//        simpleBlockingClient.run(payload, method);
//        System.out.println("Stop SimpleBlockingClient");

        System.out.println("Run SimpleNonBlockingClient");
        SimpleNonBlockingClient simpleNonBlockingClient = new SimpleNonBlockingClient(host, port, Bearer);
        simpleNonBlockingClient.run(payload, method);
        System.out.println("Stop SimpleNonBlockingClient");

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