package com.natodev.ecommerce.domain.cart.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemCarrinhoRequestDTO(

        @NotNull(message = "Item precisa de um produto")
        UUID produtoId,

        @NotNull(message = "Quantidade é obrigatória")
        @Positive(message = "Quantidade deve ser maior que zero")
        Integer quantidade

) {}
