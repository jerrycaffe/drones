package com.musalasoft.drones.drones.exception;

import java.util.Collections;
import java.util.Map;

public class RestClientException extends AbstractException {

    Map additionalInfo;

    public RestClientException(String code, String message) {
        super(code, message);
        this.additionalInfo = Collections.emptyMap();
    }
}
