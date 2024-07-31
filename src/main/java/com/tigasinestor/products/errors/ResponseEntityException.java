package com.tigasinestor.products.errors;

import com.tigasinestor.products.model.entities.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityException extends ResponseEntityExceptionHandler {
    //Arroja una respuesta al cliente si se presenta la excepción dinámica PresentException
    @ExceptionHandler(PresentException.class)
    public ResponseEntity<ErrorResponseDTO> presentExceptionResponse(PresentException ex,
                                                                     WebRequest request) {
        ErrorResponseDTO message = new ErrorResponseDTO(ex.getStatus(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.status(ex.getStatus()).body(message);
    }

    // Arroja una lista de errores al cliente si se presenta una excepción de validación de argumentos
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    // Arroja una respuesta al cliente si intenta acceder a un recurso que no existe
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponseDTO message = new ErrorResponseDTO(HttpStatus.NOT_FOUND, "No se encontró el recurso al que desea acceder!", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    // Arroja una respuesta al cliente si se presenta una excepción general que no se ha manejado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleAllExceptions(Exception ex,
                                                                WebRequest request) {
        ErrorResponseDTO message = new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno en el servidor!", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
