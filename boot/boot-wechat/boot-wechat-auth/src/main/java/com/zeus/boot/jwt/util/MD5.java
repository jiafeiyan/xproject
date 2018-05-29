package com.zeus.boot.jwt.util;

import org.springframework.util.DigestUtils;

public class MD5 {
    public static void main(String[] args) {
        String password = "chenyan";
        System.out.println(DigestUtils.md5DigestAsHex((password).getBytes()));
    }
}
