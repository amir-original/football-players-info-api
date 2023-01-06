package com.rest.apidemo.helper;

public interface HttpApi {

    HttpRequestHandler target(String baseUri);
    HttpRequestHandler path(String endpoint);
    HttpRequestHandler get();
    HttpRequestHandler post(Object entity);
    HttpRequestHandler put(Object entity);
    HttpRequestHandler delete();
}
