package org.codeflix.admin.catalogo.application.category.retrieve.list;

import org.codeflix.admin.catalogo.application.UseCase;
import org.codeflix.admin.catalogo.domain.category.CategorySearchQuery;
import org.codeflix.admin.catalogo.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutput>> {
}
