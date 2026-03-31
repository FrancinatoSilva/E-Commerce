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


}
