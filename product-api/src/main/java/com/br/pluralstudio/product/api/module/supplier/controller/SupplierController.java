package com.br.pluralstudio.product.api.module.supplier.controller;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody(required = false) SupplierRequest request) throws ValidationException {
        return supplierService.save(request);

    }

    @GetMapping
    public List<SupplierResponse> findAll(){
        return supplierService.findAll();

    }

    @GetMapping("{id}")
    public SupplierResponse findById(@PathVariable Integer id){
        return supplierService.findByIdResponse(id);

    }

    @GetMapping("name/{name}")
    public List<SupplierResponse> findByDescription(@PathVariable String name){
        return supplierService.findByName(name);

    }

    @DeleteMapping("{id}")
    public SuccessResponse delete(@PathVariable Integer id){
        return supplierService.delete(id);

    }

    @PutMapping("{id}")
    public SupplierResponse update(@PathVariable Integer id, @RequestBody(required = false) SupplierRequest request){
        return supplierService.update(request,id);

    }
}
