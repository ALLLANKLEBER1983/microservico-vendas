package com.br.pluralstudio.product.api.module.supplier.repository;

import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    List<Supplier> findByNameIgnoreCaseContaining(String name);
}
