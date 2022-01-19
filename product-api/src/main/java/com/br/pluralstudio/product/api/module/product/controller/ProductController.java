package com.br.pluralstudio.product.api.module.product.controller;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import com.br.pluralstudio.product.api.module.product.dto.ProductRequest;
import com.br.pluralstudio.product.api.module.product.dto.ProductResponse;
import com.br.pluralstudio.product.api.module.product.service.ProductService;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    @Autowired
    private ProductService service;

    @PostMapping
    public ProductResponse save(@RequestBody(required = false) ProductRequest request) throws ValidationException {
        return service.save(request);

    }

    @GetMapping
    public List<ProductResponse> findAll(){
        return service.findAll();

    }

    @GetMapping("{id}")
    public ProductResponse findById(@PathVariable Integer id){
        return service.findByIdResponse(id);

    }

    @GetMapping("name/{name}")
    public List<ProductResponse> findByDescription(@PathVariable String name){
        return service.findByName(name);

    }

    @GetMapping("category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId){
        return service.findByCategoryId(categoryId);

    }

    @GetMapping("supplier/{supplierId}")
    public List<ProductResponse> findByDescription(@PathVariable Integer supplierId){
        return service.findBySupplierId(supplierId);

    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id){
        return service.delete(id);

    }

    @PutMapping("{id}")
    public ProductResponse update(@PathVariable Integer id,@RequestBody(required = false) ProductRequest request){
        return service.update(request,id);

    }
}
