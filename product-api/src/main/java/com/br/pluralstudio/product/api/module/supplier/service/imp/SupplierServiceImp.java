package com.br.pluralstudio.product.api.module.supplier.service.imp;

import com.br.pluralstudio.product.api.module.category.config.ValidationException;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import com.br.pluralstudio.product.api.module.supplier.repository.SupplierRepository;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class SupplierServiceImp implements SupplierService {



    @Autowired
    private SupplierRepository repository;

    @Override
    public Supplier findById(Long id){
        return  repository
                .findById(id)
                .orElseThrow(() -> new ValidationException("Thers no Supplier for the given ID"));
    }


    @Override
    public SupplierResponse save(SupplierRequest request) throws ValidationException {
        validateSupplierNameInformed(request);
        var supplier = repository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);

    }


    private void validateSupplierNameInformed(SupplierRequest request) throws ValidationException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ValidationException("The suppliers description was not informed.");
        }


    }
}



