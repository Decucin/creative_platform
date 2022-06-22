package com.power.platform.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JWTTokenUtils {
    // token头
    public static final String TOKEN_HEADER = "Authorization";
    // token前缀
    public static final String TOKEN_PREFIX = "Bearer ";

    // 加密,这个特别重要
    private static final String SECRET = "create_platform";
    // jwt的发行人
    private static final String ISS = "power";

    // 过期时间1day
    private static final long EXPIRATION = 86400L;

    public static String createToken(Integer id, Boolean rememberMe) {
        /**
         *  @author decucin
         *  @date 2021/10/25 12:07
         **/
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        // 根据是否记住我选择过期时间
        long expiration = rememberMe ? EXPIRATION * 7 : EXPIRATION;
        return Jwts.builder()
                // 这里设置了签名算法
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 这里设置了签名的发行人
                .setIssuer(ISS)
                // 这里设置了加密的东西
                .setClaims(claims)
                // 这里设置了签名的时间
                .setIssuedAt(new Date())
                // 这里设置了过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    public static Integer getIdByToken(String token){
        return (Integer) Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("id");
    }
}
