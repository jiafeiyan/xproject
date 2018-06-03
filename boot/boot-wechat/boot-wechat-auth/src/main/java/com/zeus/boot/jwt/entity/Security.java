package com.zeus.boot.jwt.entity;

public class Security {
    public final static String header = "Authorization";
    public final static String secret = "spring-security-@Jwt!&Secret^#";
    public final static String tokenHead = "Bearer ";
    public final static long expiration=  10800;
}
