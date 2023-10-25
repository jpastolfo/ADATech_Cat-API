package br.com.meow.meow.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
    private HttpStatusCode statusCode;
    private String message;
}
