package org.treeleaf.config;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;
import org.treeleaf.entity.User;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String jwtSecret = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private final long jwtExpirationMs = 1000 * 60 * 30;

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date();
        Date expiry = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .claim("userId", user.getId())
                .claim("role", user.getRole().name())
                .claim("permissions", user.getPermissions())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
