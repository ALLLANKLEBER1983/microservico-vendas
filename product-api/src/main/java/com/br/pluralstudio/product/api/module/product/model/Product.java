package com.br.pluralstudio.product.api.module.product.model;

import com.br.pluralstudio.product.api.module.category.model.Category;
import com.br.pluralstudio.product.api.module.supplier.model.Supplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private Integer quantityAvailable;

    @ManyToOne
    @JoinColumn(name = "fk_supplier")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "fk_category")
    private Category category;


}
