package com.tigasinestor.products.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorResponseDTO {
    private Date exceptionDate= new Date();
    private HttpStatus status;
    private String message;
    private String url;

    public ErrorResponseDTO(HttpStatus status, String message, String url) {
        this.status = status;
        this.message = message;
        this.url = url.replace("uri=", "");
    }
}
