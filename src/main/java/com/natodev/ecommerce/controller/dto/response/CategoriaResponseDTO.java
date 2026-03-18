package com.natodev.ecommerce.controller.dto.response;

import java.util.UUID;

public record CategoriaResponseDTO(

        UUID categoriaId,
        String nome,
        String descricao
) {}
