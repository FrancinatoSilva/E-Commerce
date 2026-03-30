package com.natodev.ecommerce.domain.order.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(

        @NotNull(message = "Todo pedido precisa de um usuario")
        UUID usuarioId,

        @NotNull(message = "Todo pedido precisa de pelo menos um item")
        @Size(min = 1, message = "Todo pedido precisa de pelo menos um item")
        List<ItemPedidoRequestDTO> itensPedidos

) {}
