package com.br.pluralstudio.product.api.module.product.service.imp;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import com.br.pluralstudio.product.api.module.product.dto.ProductRequest;
import com.br.pluralstudio.product.api.module.product.dto.ProductResponse;
import com.br.pluralstudio.product.api.module.product.model.Product;
import com.br.pluralstudio.product.api.module.product.repository.ProductRepository;
import com.br.pluralstudio.product.api.module.product.service.ProductService;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class ProductServiceImp implements ProductService {


    private static final Integer ZERO = 0;


    @Autowired
    private ProductRepository repository;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private CategoryService categoryService;


    @Override
    public ProductResponse save(ProductRequest request) throws ValidationException {
        validateProductDataInformed(request);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = repository.save(Product.of(request,supplier,category));
        return ProductResponse.of(product);

    }

    @Override
    public ProductResponse update(ProductRequest request,Integer id) throws ValidationException {
        validateProductDataInformed(request);
        validateInformedId(id);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = Product.of(request,supplier,category);
        product.setId(id);
        repository.save(product);
        return ProductResponse.of(product);

    }


    private void validateProductDataInformed(ProductRequest request) {
        if (isEmpty(request.getName())) {
            throw new ValidationException("The product's name was not informed.");
        }
        if (isEmpty(request.getQuantityAvailable())) {
            throw new ValidationException("The product's quantity was not informed.");
        }
        if (request.getQuantityAvailable() <= ZERO) {
            throw new ValidationException("The quantity should not be less or equal to zero.");
        }
    }

    private void validateCategoryAndSupplierIdInformed(ProductRequest request) {
        if (isEmpty(request.getCategoryId())) {
            throw new ValidationException("The category ID was not informed.");
        }
        if (isEmpty(request.getSupplierId())) {
            throw new ValidationException("The supplier ID was not informed.");
        }
    }

    @Override
    public ProductResponse findByIdResponse(Integer id) {
        return ProductResponse.of( findById(id));
    }

    @Override
    public Product findById(Integer id){
       validateInformedId(id);
        return  repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("Thers no Product for the given ID"));
    }

    @Override
    public List<ProductResponse> findByName(String name){
        if(isEmpty(name)){
            throw new ValidationException("The Product name must be informed.");
        }
        return repository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findBySupplierId(Integer supplierId){
        if(isEmpty(supplierId)){
            throw new ValidationException("The Supplier id must be informed.");
        }
        return repository
                .findBySupplierId(supplierId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByCategoryId(Integer categoryId){
        if(isEmpty(categoryId)){
            throw new ValidationException("The category id must be informed.");
        }
        return repository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findAll(){
        return repository
                .findAll()
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }


    @Override
    public Boolean existsByCategoryId(Integer categoryId){
       return repository.existsByCategoryId(categoryId);
    }

    @Override
    public Boolean existsBySupplierId(Integer supplierId){
        return repository.existsBySupplierId(supplierId);
    }

    @Override
    public SuccessResponse delete(Integer id){
        validateInformedId(id);
        repository.deleteById(id);
        return SuccessResponse.create("The product was delete.");
    }

    private void validateInformedId(Integer id){
        if(isEmpty(id)){
            throw new ValidationException("The product ID must be informed.");
        }
    }


}



