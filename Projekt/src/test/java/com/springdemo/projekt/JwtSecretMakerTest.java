package com.springdemo.projekt;


import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;
import io.jsonwebtoken.*;
import javax.crypto.SecretKey;

public class JwtSecretMakerTest {

    @Test
    public void generateSecretKey(){
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String encodedKey = DatatypeConverter.printHexBinary(key.getEncoded());
        System.out.println(encodedKey);
    }
}
