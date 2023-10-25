package br.com.meow.meow.exception;

import feign.FeignException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ExceptionDto> NotFoundException(ChangeSetPersister.NotFoundException e) {

        return ResponseEntity.status((HttpStatus.NOT_FOUND)).body(
                new ExceptionDto(HttpStatus.NOT_FOUND, e.getMessage())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ExceptionDto> ForbiddenException(FeignException.Forbidden e){

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ExceptionDto(HttpStatus.FORBIDDEN, e.getMessage())
        );
    }
/*
    @org.springframework.web.bind.annotation.ExceptionHandler(BadRequestException)
    public ResponseEntity<ExceptionDto> BadRequestException(BadRequestException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }
 */

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDto> IllegalArgumentException(IllegalArgumentException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionDto> DataIntegrityViolationException(DataIntegrityViolationException e){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage())
        );
    }

}
