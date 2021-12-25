package com.br.pluralstudio.product.api.module.category.service;

import com.br.pluralstudio.product.api.module.category.config.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.model.Category;

public interface CategoryService {

    CategoryResponse save(CategoryRequest request) throws ValidationException;

    public Category findById(Long id);
}
