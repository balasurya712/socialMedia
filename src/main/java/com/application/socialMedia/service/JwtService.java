package com.application.socialMedia.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private static final String SECURITY_KEY = "C2380E01ED58E828F5DFB2EFDB45ECE23262E6217CE55500584DCE78862F97F3";

    public String extractUserName(String token) {
        return extractAllClaims(token).getSubject();
    }

    public <T> T extractClaims(String token,Function<Claims,T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                //    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    public boolean isTokenvalid(String token , UserDetails userDetails)
    {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) ;
        // && !isTokenExpired(token);
    }

    // private boolean isTokenExpired(String token) {
    //     return extractExpiration(token).before(new Date());
    // }

    // private Date extractExpiration(String token) {
    //     return extractClaims(token, Claims::getExpiration);
    // }

    private Claims extractAllClaims(String token){
        final Claims claims = Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
            return claims;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECURITY_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
