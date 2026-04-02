package com.natodev.ecommerce.domain.user.presentation.controller;

import com.natodev.ecommerce.domain.user.application.UsuarioService;
import com.natodev.ecommerce.domain.user.presentation.dto.request.UsuarioRequestDTO;
import com.natodev.ecommerce.domain.user.presentation.dto.response.UsuarioResponseDTO;
import com.natodev.ecommerce.shared.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<ApiResponse> salvarUsuario(
            @Valid @RequestBody UsuarioRequestDTO request) {

        UsuarioResponseDTO response = usuarioService.salvarUsuario(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.successo(response));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UsuarioResponseDTO>>> listarUsuarios() {

        List<UsuarioResponseDTO> response = usuarioService.listarUsuarios();
        return ResponseEntity
                .ok(ApiResponse.successo(response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> buscarUsuarioPorId(
            @PathVariable UUID id) {

        UsuarioResponseDTO response = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity
                .ok(ApiResponse.successo(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuarioResponseDTO>> atualizarUsuario(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {

        UsuarioResponseDTO response = usuarioService.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity
                .ok(ApiResponse.successo(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletarUsuarioPorId(@PathVariable UUID id) {

        usuarioService.deletarUsuario(id);
        return ResponseEntity
                .noContent()
                .build();
    }



}
