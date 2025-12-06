package com.ncuindia.edu.supplier.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class SupplierServiceFilter extends OncePerRequestFilter {

    @Value("${apigateway.shared.secret}")
    private String sharedSecret;

    @Value("${spring.security.user.name}")
    private String supplierUser;

    @Value("${spring.security.user.password}")
    private String supplierPass;

    private String expectedBasicAuthHeader() {
        String cred = supplierUser + ":" + supplierPass;
        String encoded = Base64.getEncoder().encodeToString(cred.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encoded;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return false;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        final String incomingSecret = request.getHeader("X-API-GATEWAY-SECRET");
        if (incomingSecret == null || !incomingSecret.equals(sharedSecret)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing/invalid gateway secret");
            return;
        }

        final String incomingAuth = request.getHeader(HttpHeaders.AUTHORIZATION);
        String expected = expectedBasicAuthHeader();
        if (incomingAuth == null || !incomingAuth.equals(expected)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing/invalid Authorization");
            return;
        }
        
        filterChain.doFilter(request, response);
    }
}
