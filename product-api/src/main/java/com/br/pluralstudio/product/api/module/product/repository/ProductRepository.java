package com.br.pluralstudio.product.api.module.product.repository;

import com.br.pluralstudio.product.api.module.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
