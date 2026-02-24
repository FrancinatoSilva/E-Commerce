package com.natodev.ecommerce.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pedido_id")
    private UUID pedidoId;

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedidos;

}
