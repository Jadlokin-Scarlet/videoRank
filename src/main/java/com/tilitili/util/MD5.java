package com.tilitili.util;

import org.springframework.util.DigestUtils;

public class MD5 {
    public static String md5 (String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }
}
