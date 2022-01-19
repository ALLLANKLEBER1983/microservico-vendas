package com.br.pluralstudio.product.api.module.category.service;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.model.Category;

import java.util.List;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request) throws ValidationException;
    Category findById(Integer id);
    List<CategoryResponse> findByDescription(String description);
    CategoryResponse findByIdResponse(Integer id);
    List<CategoryResponse> findAll();
    SuccessResponse delete(Integer id);
    CategoryResponse update(CategoryRequest request, Integer id) throws ValidationException;

}
