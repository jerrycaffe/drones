package com.musalasoft.drones.drones.exception;


public class BadRequestException extends AbstractException {

    public BadRequestException(String message) {
        this("405", message);
    }

    public BadRequestException(String code, String message) {
        super(code, message);
    }
}
