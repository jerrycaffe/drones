package com.musalasoft.drones.drones.exception;


import static com.musalasoft.drones.drones.dto.ErrorCodes.NOT_FOUND_ERROR_CODE;

public class NotFoundException extends AbstractException {

    public NotFoundException(String message) {
        this(NOT_FOUND_ERROR_CODE, message);
    }

    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
