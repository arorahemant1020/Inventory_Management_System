package com.ncuindia.edu.apigateway.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthHeaderFactory {

    @Value("${product.auth.username}")
    String _ProductUsername;
    @Value("${product.auth.password}")
    String _ProductPassword;

    @Value("${supplier.auth.username}")
    String _SupplierUsername;
    @Value("${supplier.auth.password}")
    String _SupplierPassword;

    @Value("${apigateway.shared.secret}")
    String _SharedSecret;
    
    String BuildAuthHeader(String serviceName)
    {
        String username = "";
        String password = "";

        if(serviceName == "product")
        {
            username = _ProductUsername; 
            password = _ProductPassword;
        }
        else if(serviceName == "supplier")
        {
            username = _SupplierUsername; 
            password = _SupplierPassword;
        }

        String auth = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
    }

    String getSharedSecret()
    {
        return _SharedSecret;
    }
}