package com.OOP.remittancesystem.exception;

public class FileStorageException extends RuntimeException{

    // custom exception with causes
    public FileStorageException(String message){
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message,cause);
    }
}
