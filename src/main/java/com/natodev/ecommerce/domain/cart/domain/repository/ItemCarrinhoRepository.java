package com.natodev.ecommerce.domain.cart.domain.repository;

import com.natodev.ecommerce.domain.cart.domain.entity.Carrinho;
import com.natodev.ecommerce.domain.cart.domain.entity.ItemCarrinho;
import com.natodev.ecommerce.domain.product.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, UUID> {

    List<ItemCarrinho> findByCarrinho(Carrinho carrinho);
    List<ItemCarrinho> findByProduto(Produto produto);
}
