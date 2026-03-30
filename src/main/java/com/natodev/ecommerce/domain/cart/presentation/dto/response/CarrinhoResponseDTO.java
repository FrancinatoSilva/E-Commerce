package com.natodev.ecommerce.domain.cart.presentation.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record CarrinhoResponseDTO(

        UUID carrinhoId,
        LocalDate dataCriacao,
        String status
) {}
