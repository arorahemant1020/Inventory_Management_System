package com.ncu.edu.authservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ncu.edu.authservice.dto.AuthDto;
import com.ncu.edu.authservice.dto.ReturnDto;
import com.ncu.edu.authservice.dto.SignupDto;
import com.ncu.edu.authservice.exceptions.DatabaseException;
import com.ncu.edu.authservice.exceptions.ValidationException;
import com.ncu.edu.authservice.repository.AuthRepository;

@Service
public class AuthService {

    private final AuthRepository _AuthRepository;
    private final PasswordEncoder _PasswordEncoder;

    @Autowired
    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder) {
        this._AuthRepository = authRepository;
        this._PasswordEncoder = passwordEncoder;
    }

    public boolean SignUp(SignupDto cred, ReturnDto response) {
        if (cred == null) {
            throw new ValidationException("Signup payload cannot be null");
        }
        if (cred.get_Email() == null || cred.get_Email().trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (cred.get_Password() == null || cred.get_Password().trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }

        try {
            cred.set_Password(_PasswordEncoder.encode(cred.get_Password()));

            StringBuffer status = new StringBuffer();
            boolean isSuccess = _AuthRepository.SignUp(cred, status);

            if (isSuccess) {
                response.set_Status("User registration successful.");
            } else {
                response.set_Status("User registration failed: " + status.toString());
            }
            response.set_Email(cred.get_Email());
            return isSuccess;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to register user: " + ex.getMessage(), ex);
        }
    }

    public Boolean Authenticate(AuthDto cred) {
        if (cred == null) {
            throw new ValidationException("Authentication payload cannot be null");
        }
        String email = cred.get_Email();
        String password = cred.get_Password();

        if (email == null || email.trim().isEmpty()) {
            throw new ValidationException("Email is required");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("Password is required");
        }

        try {
            StringBuffer status = new StringBuffer();
            StringBuffer passwordFromDB = new StringBuffer();

            Boolean repoSuccess = _AuthRepository.getPasswordFromEmail(email, passwordFromDB, status);
            if (!repoSuccess) {
                if (status.length() > 0) {
                    throw new DatabaseException("Repository error while authenticating: " + status.toString());
                }
                return false;
            }
            return _PasswordEncoder.matches(password, passwordFromDB.toString());
        } catch (DatabaseException dbe) {
            throw dbe;
        } catch (Exception ex) {
            throw new DatabaseException("Failed during authentication: " + ex.getMessage(), ex);
        }
    }
}
