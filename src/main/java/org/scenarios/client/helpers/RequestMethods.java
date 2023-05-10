package org.scenarios.client.helpers;

public enum RequestMethods {
    GET("GET"),
    POST("POST"),
    PUT("PUT");

    private final String methodName;
    RequestMethods(String name) {
        this.methodName = name;
    }

    @Override
    public String toString() {
        return this.methodName;
    }
}
