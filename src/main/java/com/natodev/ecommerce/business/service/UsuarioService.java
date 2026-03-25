package com.natodev.ecommerce.business.service;

import com.natodev.ecommerce.controller.dto.request.UsuarioRequestDTO;
import com.natodev.ecommerce.controller.dto.response.UsuarioResponseDTO;
import com.natodev.ecommerce.infrastructure.entity.Usuario;
import com.natodev.ecommerce.infrastructure.exception.EmailCadastradoException;
import com.natodev.ecommerce.infrastructure.exception.UsuarioNaoEncontradoException;
import com.natodev.ecommerce.infrastructure.repository.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        if (usuarioRepository.existsByEmail(usuarioRequestDTO.email())){
            throw new EmailCadastradoException("E-mail " + usuarioRequestDTO.email() + " já está cadastrado!");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioSalvo.getUsuarioId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(),
                usuarioSalvo.getDataCriacao());

    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listarUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getUsuarioId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDataCriacao()
                ))
                .collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarUsuarioPorId(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com id " + usuarioId +
                        " não foi encontrado!"));

        return new UsuarioResponseDTO(usuario.getUsuarioId(), usuario.getNome(), usuario.getEmail(), usuario.getDataCriacao());
    }

    @Transactional
    public UsuarioResponseDTO atualizarUsuario(UUID usuarioId, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario com id " + usuarioId +
                        " não foi encontrado!"));

        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioAtualizado.getUsuarioId(), usuarioAtualizado.getNome(), usuarioAtualizado.getEmail(),
                usuarioAtualizado.getDataCriacao());
    }
}
