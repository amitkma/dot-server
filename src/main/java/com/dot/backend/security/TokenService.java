package com.dot.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenService {

    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

    @Autowired
    static Environment environment;

    public static void addAuthToken(HttpServletResponse response, String phoneNumber, String secretKey, String salt) {
        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(secretKey.getBytes("UTF-8"));
            byte[] key = md.digest();
            Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());
            System.out.println(signingKey);
            String userId = generateUniqueId(phoneNumber, salt);

            //Let's set the JWT Claims
            JwtBuilder builder = Jwts.builder().setId(userId)
                    .setIssuedAt(now)
                    .setSubject("users/"+userId)
                    .setIssuer("dot-salons")
                    .signWith(signingKey, signatureAlgorithm);
            Map<String, Object> claims = new HashMap<>();
            claims.put("access", "full");
            builder.addClaims(claims);
            String jwt = builder.compact();
            //Builds the JWT and serializes it to a compact, URL-safe string
            response.addHeader(HEADER_STRING, TOKEN_PREFIX+" "+ jwt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static Claims validateToken(String token){
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(environment.getProperty("SECRET_KEY")))
                .parseClaimsJws(token).getBody();
    }

    public static String generateUniqueId(String phoneNumber, String salt) {
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update((phoneNumber+salt).getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
