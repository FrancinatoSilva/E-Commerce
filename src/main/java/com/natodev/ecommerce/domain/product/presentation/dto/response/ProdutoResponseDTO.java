package com.natodev.ecommerce.domain.product.presentation.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(

        UUID produtoId,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque,
        String nomeCategoria


) {}
