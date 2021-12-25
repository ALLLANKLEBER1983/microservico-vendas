package com.br.pluralstudio.product.api.module.supplier.repository;

import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
