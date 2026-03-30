package com.natodev.ecommerce.domain.order.presentation.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(

        UUID pedidoId,
        String nomeUsuario,
        LocalDate dataPedido,
        BigDecimal valorTotal,
        String status,
        List<ItemPedidoResponseDTO> itensPedidos
) {}
