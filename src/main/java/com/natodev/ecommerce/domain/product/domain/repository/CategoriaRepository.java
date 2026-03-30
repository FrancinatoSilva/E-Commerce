package com.natodev.ecommerce.domain.product.domain.repository;

import com.natodev.ecommerce.domain.product.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

}
