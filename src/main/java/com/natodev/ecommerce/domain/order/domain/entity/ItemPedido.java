package com.natodev.ecommerce.domain.order.domain.entity;

import com.natodev.ecommerce.domain.product.domain.entity.Produto;
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
    @JoinColumn (name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn (name = "produto_id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

}
