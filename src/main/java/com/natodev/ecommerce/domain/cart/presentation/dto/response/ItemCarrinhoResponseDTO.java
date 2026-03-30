package com.natodev.ecommerce.domain.cart.presentation.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemCarrinhoResponseDTO(

        UUID itemCarrinhoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal precoUnitario
) {}
