package com.natodev.ecommerce.domain.product.presentation.dto.response;

import java.util.UUID;

public record CategoriaResponseDTO(

        UUID categoriaId,
        String nome,
        String descricao
) {}
