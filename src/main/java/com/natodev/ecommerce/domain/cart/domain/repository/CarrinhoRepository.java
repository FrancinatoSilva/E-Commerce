package com.natodev.ecommerce.domain.cart.domain.repository;

import com.natodev.ecommerce.domain.cart.domain.entity.Carrinho;
import com.natodev.ecommerce.domain.user.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {

    Optional<Carrinho> findByUsuario(Usuario usuario);

}
