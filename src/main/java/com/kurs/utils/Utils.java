package com.kurs.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils {

    public static void main(String args[]) throws Exception {
        String cryptedPassword = new BCryptPasswordEncoder().encode("1456");
        System.out.println(cryptedPassword);
    }
}