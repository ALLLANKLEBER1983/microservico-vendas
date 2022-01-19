package com.br.pluralstudio.product.api.module.supplier.service.imp;

import com.br.pluralstudio.product.api.config.exception.SuccessResponse;
import com.br.pluralstudio.product.api.config.exception.ValidationException;
import com.br.pluralstudio.product.api.module.product.service.ProductService;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import com.br.pluralstudio.product.api.module.supplier.repository.SupplierRepository;
import com.br.pluralstudio.product.api.module.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
public class SupplierServiceImp implements SupplierService {



    @Autowired
    private SupplierRepository repository;

    @Autowired
    private ProductService productService;

    @Override
    public SupplierResponse findByIdResponse(Integer id) {
        return SupplierResponse.of( findById(id));
    }

    @Override
    public List<SupplierResponse> findByName(String name){
        if(isEmpty(name)){
            throw new ValidationException("The Supplier name must be informed.");
        }
        return repository
                .findByNameIgnoreCaseContaining(name)
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierResponse> findAll(){
        return repository
                .findAll()
                .stream()
                .map(SupplierResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public Supplier findById(Integer id){
        validateInformedId(id);
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

    @Override
    public SupplierResponse update(SupplierRequest request,Integer id) throws ValidationException {
        validateSupplierNameInformed(request);
        validateInformedId(id);
        var supplier = Supplier.of(request);
        supplier.setId(id);
        repository.save(Supplier.of(request));
        return SupplierResponse.of(supplier);

    }

    @Override
    public SuccessResponse delete(Integer id){
        validateInformedId(id);
        if(productService.existsBySupplierId(id)){
            throw new ValidationException("you cannot delete this supplier because its already defined by a product");
        }
        repository.deleteById(id);
        return SuccessResponse.create("The supplier was delete.");
    }

    private void validateInformedId(Integer id){
        if(isEmpty(id)){
            throw new ValidationException("The supplier ID must be informed.");
        }
    }


    private void validateSupplierNameInformed(SupplierRequest request) throws ValidationException {
        if (ObjectUtils.isEmpty(request)) {
            throw new ValidationException("The suppliers description was not informed.");
        }
    }


}



