package com.br.pluralstudio.product.api.module.supplier.controller;

import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public SupplierResponse save(@RequestBody(required = false) SupplierRequest request) throws ValidationException {
        return supplierService.save(request);

    }
}
