package com.natodev.ecommerce.domain.product.presentation.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
        String nome,

        @NotBlank(message = "Descrição é obrigatória")
        @Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
        String descricao,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "Preço nunca pode ser zero ou negativo")
        BigDecimal preco,

        @NotNull(message = "Estoque é obrigatório")
        @PositiveOrZero(message = "Pode ser zero, mas nunca negativo")
        Integer estoque,

        @NotNull(message = "Todo produto precisa de categoria")
        UUID categoriaProduto
) {}
