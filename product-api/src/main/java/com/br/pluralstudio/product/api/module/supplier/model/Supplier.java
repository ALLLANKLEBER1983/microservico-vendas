package com.br.pluralstudio.product.api.module.supplier.model;

import com.br.pluralstudio.product.api.module.category.dto.CategoryRequest;
import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    public static Supplier of(SupplierRequest request){
        var suplier = new Supplier();
        BeanUtils.copyProperties(request,suplier);
        return suplier;
    }

}
