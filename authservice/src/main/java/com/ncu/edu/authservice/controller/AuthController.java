package com.ncu.edu.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncu.edu.authservice.dto.AuthDto;
import com.ncu.edu.authservice.dto.ReturnDto;
import com.ncu.edu.authservice.dto.SignupDto;
import com.ncu.edu.authservice.exceptions.DatabaseException;
import com.ncu.edu.authservice.exceptions.ValidationException;
import com.ncu.edu.authservice.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService _AuthService;

    @Autowired
    public AuthController(AuthService authService) {
        this._AuthService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ReturnDto> SignUp(@RequestBody SignupDto cred, HttpServletRequest request) {
        ReturnDto response = new ReturnDto();
        try {
            boolean isSuccess = _AuthService.SignUp(cred, response);
            if (isSuccess) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (ValidationException vex) {
            response.set_Email(cred != null ? cred.get_Email() : "unknown");
            response.set_Status("Signup failed: " + vex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (DatabaseException dbex) {
            response.set_Email(cred != null ? cred.get_Email() : "unknown");
            response.set_Status("Signup failed: " + dbex.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        } catch (Exception ex) {
            response.set_Email(cred != null ? cred.get_Email() : "unknown");
            response.set_Status("Signup failed: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> Authenticate(@RequestBody AuthDto cred) {
        try {
            boolean isAuthenticated = _AuthService.Authenticate(cred);
            if (isAuthenticated) {
                return ResponseEntity.ok("Authentication successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
            }
        } catch (ValidationException vex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Authentication failed: " + vex.getMessage());
        } catch (DatabaseException dbex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Authentication failed: " + dbex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Authentication failed: " + ex.getMessage());
        }
    }
}
