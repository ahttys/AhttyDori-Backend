package com.ahttys.server.config.jwt;

import com.ahttys.server.domain.Member.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {
    private static final long time = 120;

    private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * time;
//    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;
    private final UserDetailsService userDetailsService;

    public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
        logger.info(secretKey);
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userDetailsService = userDetailsService;
    }

    public String createToken(Member member) {
        long now = (new Date()).getTime();
        Date validity = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(String.valueOf(member.getId()))
                .claim("name", member.getName())
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetail = userDetailsService.loadUserByUsername(parseClaims(token).getSubject());
        return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            logger.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            logger.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            logger.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            logger.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}