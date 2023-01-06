package com.rest.apidemo.filter;

import com.rest.apidemo.helper.PropertiesReader;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@PreMatching
public class BarerTokenFilter implements ContainerRequestFilter {

    private String token;

    public BarerTokenFilter() {
        loadAppConfig();
    }

    private void loadAppConfig() {
        PropertiesReader reader = new PropertiesReader("app-config");
        token = reader.getProperty("token");
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.equals(token))
            throw new NotAuthorizedException("Barer");
    }
}
