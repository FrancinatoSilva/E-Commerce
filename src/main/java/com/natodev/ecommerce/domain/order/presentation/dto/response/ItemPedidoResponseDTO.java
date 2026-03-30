package com.natodev.ecommerce.domain.order.presentation.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemPedidoResponseDTO(

        UUID itemPedidoId,
        String nomeProduto,
        Integer quantidade,
        BigDecimal precoUnitario,
        BigDecimal subTotal

) {}
