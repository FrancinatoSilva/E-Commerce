package com.natodev.ecommerce.shared.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private boolean success;

    @Builder.Default
    private String message = "Requisição processada com sucesso!";
    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> successo(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setMessage("Sucesso");
        response.setData(data);
        response.setTimestamp(LocalDateTime.now());

        return response;

    }

    public static <T> ApiResponse<T> erro(String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(message);
        response.setData(null);
        response.setTimestamp(LocalDateTime.now());

        return response;
    }




}
