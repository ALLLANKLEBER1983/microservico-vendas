package com.br.pluralstudio.product.api.module.product.service;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.product.dto.ProductRequest;
import com.br.pluralstudio.product.api.module.product.dto.ProductResponse;
import com.br.pluralstudio.product.api.module.product.model.Product;

import java.util.List;

public interface ProductService {

    ProductResponse save(ProductRequest request);

    ProductResponse findByIdResponse(Integer id);

    Product findById(Integer id);

    List<ProductResponse> findByName(String name);

    List<ProductResponse> findBySupplierId(Integer supplierId);

    List<ProductResponse> findByCategoryId(Integer categoryId);

    List<ProductResponse> findAll();

    Boolean existsByCategoryId(Integer categoryId);

    Boolean existsBySupplierId(Integer supplierId);

    SuccessResponse delete(Integer id);

    ProductResponse update(ProductRequest request,Integer id) throws ValidationException;
}
