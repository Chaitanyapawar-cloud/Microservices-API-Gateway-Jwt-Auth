package com.csp.api.filter;

import java.util.List;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class Validator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/saveUser",
            "/auth/token",
            "/eureka"
    );

    public boolean isSecured(ServerHttpRequest serverHttpRequest) {
            return openApiEndpoints
                    .stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
    }

}