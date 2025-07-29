package com.springdemo.projekt.webtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    private static final String SECRET = "D65DA62D4FC7D0E539940070F8038A1816823B8833AB3F4068EA6C7AF3FF4AFEE47E0B020AA64C37288853AB0DBF3CD1F416A01611B89253955444F764AA787C";
    private static final long VALIDITY = TimeUnit.MINUTES.toSeconds(1);

    public String generateToken(UserDetails userDetails) {
       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(VALIDITY)))
                .signWith(generateKey())
                .compact();

    }

    private SecretKey generateKey(){
        byte[] decodeKey = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(decodeKey);
    }


    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(generateKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
