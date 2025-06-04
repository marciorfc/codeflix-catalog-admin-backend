package org.codeflix.admin.catalogo.domain.category;

import org.codeflix.admin.catalogo.domain.pagination.Pagination;

public interface CategoryGateway {

    Category create(Category aCategory);

    void deleteById(CategoryID anId);

    Opetional<Category> findById(CategoryID anId);

    Pagination<Category> findAll(CategorySearchQuery aQuery);
}
