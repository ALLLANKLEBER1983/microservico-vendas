package com.br.pluralstudio.product.api.module.category.controller;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody(required = false) CategoryRequest request) throws ValidationException {
        return categoryService.save(request);

    }

    @GetMapping
    public List<CategoryResponse> findAll(){
        return categoryService.findAll();

    }

    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Integer id){
        return categoryService.findByIdResponse(id);

    }

    @GetMapping("description/{description}")
    public List<CategoryResponse> findByDescription(@PathVariable String description){
        return categoryService.findByDescription(description);

    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id){
        return categoryService.delete(id);

    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable Integer id,@RequestBody(required = false) CategoryRequest request){
        return categoryService.update(request,id);

    }

}
