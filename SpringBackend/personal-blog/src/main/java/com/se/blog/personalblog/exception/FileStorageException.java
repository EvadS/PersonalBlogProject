package com.se.blog.personalblog.exception;


import java.io.IOException;

public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String s, IOException ex) {
        super(s,ex);
    }
}
