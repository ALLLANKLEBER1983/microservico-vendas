package com.br.pluralstudio.product.api.module.category.service.imp;

import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.category.config.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.repository.CategoryRepository;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class CategoryServiceImp implements CategoryService {



    @Autowired
    private CategoryRepository repository;

    @Override
    public Category findById(Long id){
        return  repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("Thers no Supplier for the given ID"));
    }


    @Override
    public CategoryResponse save(CategoryRequest request) throws ValidationException {
        validateCategoryNameInformed(request);
        var category = repository.save(Category.of(request));
        return CategoryResponse.of(category);

    }


    private void validateCategoryNameInformed(CategoryRequest request) throws ValidationException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ValidationException("The category description was not informed.");
        }


    }
}



