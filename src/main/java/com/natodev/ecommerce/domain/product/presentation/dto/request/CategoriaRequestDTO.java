package com.natodev.ecommerce.domain.product.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoriaRequestDTO(

        @NotBlank(message = "Categoria precisa ter um nome")
        @Size(min = 3, message = "Categoria precisa ter ao menos 3 caracteres")
        String nome,

        @NotBlank(message = "Categoria precisa ter uma descricao")
        @Size(min = 12, message = "Categoria precisa ter ao menos 12 caracteres")
        String descricao

) {}
