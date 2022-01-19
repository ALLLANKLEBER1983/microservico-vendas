package com.br.pluralstudio.product.api.module.supplier.service;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;

import java.util.List;

public interface SupplierService {

    SupplierResponse save(SupplierRequest request) throws ValidationException;

    Supplier findById(Integer id);

    SupplierResponse findByIdResponse(Integer id);

    List<SupplierResponse> findByName(String name);

    List<SupplierResponse> findAll();

    SuccessResponse delete(Integer id);

    SupplierResponse update(SupplierRequest request,Integer id) throws ValidationException;


}
