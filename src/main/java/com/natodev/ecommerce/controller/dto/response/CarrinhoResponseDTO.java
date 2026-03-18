package com.natodev.ecommerce.controller.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record CarrinhoResponseDTO(

        UUID carrinhoId,
        LocalDate dataCriacao,
        String status
) {}
