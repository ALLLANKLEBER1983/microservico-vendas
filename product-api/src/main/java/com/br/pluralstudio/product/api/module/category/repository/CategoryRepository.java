package com.br.pluralstudio.product.api.module.category.repository;

import com.br.pluralstudio.product.api.module.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
