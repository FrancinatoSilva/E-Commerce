package com.natodev.ecommerce.domain.product.presentation.controller;

import com.natodev.ecommerce.domain.product.application.ProdutoService;
import com.natodev.ecommerce.domain.product.presentation.dto.request.ProdutoRequestDTO;
import com.natodev.ecommerce.domain.product.presentation.dto.response.ProdutoResponseDTO;
import com.natodev.ecommerce.domain.user.presentation.dto.response.UsuarioResponseDTO;
import com.natodev.ecommerce.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/produtos")
@Slf4j
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProdutoResponseDTO>> salvarProduto(
            @Valid @RequestBody ProdutoRequestDTO request) {

        ProdutoResponseDTO response = produtoService.salvarProduto(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.sucesso(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProdutoResponseDTO>>> listarProdutos() {

        List<ProdutoResponseDTO> response = produtoService.listarProdutos();
        return ResponseEntity
                .ok(ApiResponse.sucesso(response));
    }

}
