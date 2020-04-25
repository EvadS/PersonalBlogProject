package com.se.blog.personalblog.exception;

import java.net.MalformedURLException;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String s, MalformedURLException ex) {
        super(s,ex);
    }

    public FileNotFoundException(String s) {
        super(s);
    }
}
