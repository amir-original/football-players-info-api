package com.rest.apidemo.helper;

import jakarta.ws.rs.core.MediaType;

public interface HttpApi {

    HttpRequestHandler target(String baseUri);
    HttpRequestHandler path(String endpoint);
    HttpRequestHandler GET();
    HttpRequestHandler POST(Object entity);
    HttpRequestHandler PUT(Object entity);
    HttpRequestHandler DELETE();
    HttpRequestHandler mediaType(String type);
    HttpRequestHandler header(String key,String value);

}
