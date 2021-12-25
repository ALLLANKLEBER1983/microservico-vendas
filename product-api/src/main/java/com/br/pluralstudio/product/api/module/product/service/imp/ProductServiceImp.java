package com.br.pluralstudio.product.api.module.product.service.imp;

import com.br.pluralstudio.product.api.module.category.config.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.category.repository.CategoryRepository;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import com.br.pluralstudio.product.api.module.product.dto.ProductRequest;
import com.br.pluralstudio.product.api.module.product.dto.ProductResponse;
import com.br.pluralstudio.product.api.module.product.repository.ProductRepository;
import com.br.pluralstudio.product.api.module.product.service.ProductService;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class ProductServiceImp implements ProductService {



    @Autowired
    private ProductRepository repository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public ProductResponse save(ProductRequest request) throws ValidationException {
        validateProductNameInformed(request);
        var category = repository.save(Product.of(request));
        return CategoryResponse.of(category);

    }


    private void validateProductNameInformed(ProductRequest request) throws ValidationException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ValidationException("The category description was not informed.");
        }


    }
}



