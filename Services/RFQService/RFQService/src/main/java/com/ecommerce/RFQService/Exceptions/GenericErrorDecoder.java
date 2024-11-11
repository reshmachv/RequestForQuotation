package com.ecommerce.RFQService.Exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


/*public class GenericErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        switch (status) {
            case NOT_FOUND:
                return new ResourceNotFoundException("The requested resource was not found.");
            case SERVICE_UNAVAILABLE:
                return new ServiceUnavailableException("The service is currently unavailable.");
            case BAD_REQUEST:
                return new GeneralServiceException("Bad request to the service.");
            case INTERNAL_SERVER_ERROR:
                return new GeneralServiceException("Internal server error in the service.");
            default:
                return new GeneralServiceException("An unexpected error occurred.");
        }

    }
}*/
