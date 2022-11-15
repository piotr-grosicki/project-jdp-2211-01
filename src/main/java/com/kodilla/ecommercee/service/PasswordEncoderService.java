package com.kodilla.ecommercee.service;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class PasswordEncoderService {
    public String encodePassword(String password) throws NoSuchAlgorithmException, IOException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(md.digest(password.getBytes()));
        return outputStream.toString();
    }
}
