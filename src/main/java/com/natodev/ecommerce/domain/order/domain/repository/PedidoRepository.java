package com.natodev.ecommerce.domain.order.domain.repository;

import com.natodev.ecommerce.domain.order.domain.entity.Pedido;
import com.natodev.ecommerce.domain.user.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByUsuario(Usuario usuario);
}
