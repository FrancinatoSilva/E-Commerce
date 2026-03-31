package com.natodev.ecommerce.shared.handler;

import com.natodev.ecommerce.domain.product.domain.exception.CategoriaNaoExisteException;
import com.natodev.ecommerce.domain.user.domain.exception.EmailCadastradoException;
import com.natodev.ecommerce.domain.user.domain.exception.UsuarioNaoEncontradoException;
import com.natodev.ecommerce.shared.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailCadastradoException.class)
    public ResponseEntity<ApiResponse<Object>> handleEmailJaCadastrado(EmailCadastradoException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ApiResponse<Object>> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

    @ExceptionHandler(CategoriaNaoExisteException.class)
    public ResponseEntity<ApiResponse<Object>> handleCategoriaNaoExiste(CategoriaNaoExisteException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
