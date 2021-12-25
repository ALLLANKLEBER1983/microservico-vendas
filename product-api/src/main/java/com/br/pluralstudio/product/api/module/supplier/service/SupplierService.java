package com.br.pluralstudio.product.api.module.supplier.service;

import com.br.pluralstudio.product.api.module.category.config.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;

public interface SupplierService {

    SupplierResponse save(SupplierRequest request) throws ValidationException;

    public Supplier findById(Long id);
}
