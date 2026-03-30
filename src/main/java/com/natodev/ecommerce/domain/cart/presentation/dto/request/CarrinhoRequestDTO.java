package com.natodev.ecommerce.domain.cart.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CarrinhoRequestDTO(

        @NotBlank(message = "Status é obrigatório")
        @Size(min = 5, message = "Status precisa de no minimo 5 caracteres")
        String status
) {}
