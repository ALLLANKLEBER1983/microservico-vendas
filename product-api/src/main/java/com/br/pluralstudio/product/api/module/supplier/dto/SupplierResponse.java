package com.br.pluralstudio.product.api.module.supplier.dto;

import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class SupplierResponse {

    private Long id;
    private String name;

    public static SupplierResponse of(Supplier supplier){
        var response = new SupplierResponse();
        BeanUtils.copyProperties(supplier,response);
        return response;
    }
}
