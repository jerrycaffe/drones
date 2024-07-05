package com.musalasoft.drones.drones.exception;

import com.musalasoft.drones.drones.dto.Error;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractException extends RuntimeException {
    String code;
    Error error;

    public AbstractException(String code, String message) {
        super(message);
        this.code = code;
        this.error = new Error(code, message);
    }

}
