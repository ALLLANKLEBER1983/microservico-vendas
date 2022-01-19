package com.br.pluralstudio.product.api.module.category.service.imp;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.repository.CategoryRepository;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import com.br.pluralstudio.product.api.module.product.service.ProductService;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class CategoryServiceImp implements CategoryService {



    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ProductService productService;

    public List<CategoryResponse> findByDescription(String description){
        if(isEmpty(description)){
            throw new ValidationException("The category description must be informed.");
        }
        return repository
                .findByDescriptionIgnoreCaseContaining(description)
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findByIdResponse(Integer id){
        return CategoryResponse.of(findById(id));

    }

    @Override
    public List<CategoryResponse> findAll(){
        return repository
                .findAll()
                .stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }


    @Override
    public Category findById(Integer id){
        validateInformedId(id);
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

    @Override
    public CategoryResponse update(CategoryRequest request, Integer id) throws ValidationException {
        validateCategoryNameInformed(request);
        validateInformedId(id);
        var category = Category.of(request);
        category.setId(id);
        repository.save(Category.of(request));
        return CategoryResponse.of(category);

    }

    @Override
    public SuccessResponse delete(Integer id){
        validateInformedId(id);
        if(productService.existsByCategoryId(id)){
            throw new ValidationException("you cannot delete this category because its already defined by a product");
        }
        repository.deleteById(id);
        return SuccessResponse.create("The category was delete.");
    }

    private void validateInformedId(Integer id){
        if(isEmpty(id)){
            throw new ValidationException("The category ID must be informed.");
        }
    }


    private void validateCategoryNameInformed(CategoryRequest request) throws ValidationException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ValidationException("The category description was not informed.");
        }


    }
}



