package com.natodev.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "item_pedido_id")
    private UUID itemPedidoId;

    @ManyToOne
    @JoinColumn (name = "pedido_fk")
    private Pedido pedido; // Verificar lógica de forest key

    @Column(name = "produto_id")
    private Integer produtoId; // Verificar lógica de foreign key

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "sub_total")
    private BigDecimal subTotal;
}
