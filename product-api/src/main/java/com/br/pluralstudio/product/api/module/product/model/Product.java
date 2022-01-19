package com.br.pluralstudio.product.api.module.product.model;

import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.product.dto.ProductRequest;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "fk_supplier")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;

    @Column(name = "CREATED_AT",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

    public static Product of(ProductRequest request,
                             Supplier supplier,
                             Category category) {
        return Product
                .builder()
                .name(request.getName())
                .quantityAvailable(request.getQuantityAvailable())
                .supplier(supplier)
                .category(category)
                .build();
    }

}
