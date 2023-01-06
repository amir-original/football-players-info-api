package com.rest.apidemo.filter;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.MultivaluedMap;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

//TODO should be complete authentication
public class Authenticator implements ClientRequestFilter {
    private String username;
    private String password;

    public Authenticator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void filter(ClientRequestContext requestContext) {
        MultivaluedMap<String, String> headers = requestContext.getStringHeaders();
        String Authentication = getAuthentication();
        headers.add("Authorization", Authentication);
    }

    public String getAuthentication() {
        String token = username + ":" + password;
        try {
            return "Barer"+ DatatypeConverter.printBase64Binary(token.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Unable to encode with UTF-8",e);
        }

    }
}
