package org.scenarios.client;

import org.scenarios.client.helpers.RequestMethods;
import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

public class AbstractSSLClient {
    public static int port = 8243;
    public static String keyStoreLocation = "<Client keystore location>";
    public static String keyStorePassword = "<Client keystore password>";

    public void run(String payload, RequestMethods method){

    }
    protected SSLContext createSSLContext() {

        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream(keyStoreLocation), keyStorePassword.toCharArray());

            // Create key manager
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, "wso2carbon".toCharArray());
            KeyManager[] km = keyManagerFactory.getKeyManagers();

            // Create trust manager
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(keyStore);
            TrustManager[] tm = trustManagerFactory.getTrustManagers();

            // Initialize SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(km, tm, null);

            return sslContext;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
