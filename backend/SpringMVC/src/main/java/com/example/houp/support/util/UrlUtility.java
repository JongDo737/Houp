package com.example.houp.support.util;

import com.example.houp.support.util.exception.UrlDecodingException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UrlUtility {

    private static final String ENCODING = "UTF-8";

    public static String decode(String value) {
        try {
            return URLDecoder.decode(value, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new UrlDecodingException();
        }
    }
}
