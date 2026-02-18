package com.natodev.ecommerce.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "carrinho_id", unique = true)
    private Integer carrinhoId; // Verificar lógica de forest key

    @Column(name = "produto_id", unique = true)
    private Integer produtoId; // Verificar lógica de forest key

    @Column (name = "quantidade")
    private Integer quantidade;

    @Column (name = "preco_unitario")
    private BigDecimal precoUnitario;
}
