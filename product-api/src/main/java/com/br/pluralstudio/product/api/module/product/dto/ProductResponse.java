package com.br.pluralstudio.product.api.module.product.dto;

import com.br.pluralstudio.product.api.module.category.dto.CategoryResponse;
import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.product.model.Product;
import com.br.pluralstudio.product.api.module.supplier.dto.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.ProcessIdUtil;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private Integer quantityAvailable;
    private SupplierResponse supplier;
    private CategoryResponse category;


    public static ProductResponse of(Product product){
        return ProductResponse
                .builder()
                .id(product.getId())
                .name(product.getName())
                .quantityAvailable(product.getQuantityAvailable())
                .supplier(SupplierResponse.of(product.getSupplier()))
                .category(CategoryResponse.of(product.getCategory()))
                .build();
    }
}
