package org.codeflix.admin.catalogo.infrastructure.category.presenters;

import org.codeflix.admin.catalogo.application.category.retrieve.get.CategoryOutput;
import org.codeflix.admin.catalogo.infrastructure.category.models.GetCategoryApiOutput;

import java.util.function.Function;

public interface CategoryApiPresenter {

    //Equivale ao mesmo present utilizando o método estático
    //Utilizando a function equivale a uma propriedade
    Function<CategoryOutput, GetCategoryApiOutput> present =
            output -> new GetCategoryApiOutput(
            output.id().getValue(),
            output.name(),
            output.description(),
            output.isActive(),
            output.createdAt(),
            output.updatedAt(),
            output.deletedAt()
    );

//    static GetCategoryApiOutput present(final CategoryOutput output) {
//        return new GetCategoryApiOutput(
//                output.id().getValue(),
//                output.name(),
//                output.description(),
//                output.isActive(),
//                output.createdAt(),
//                output.updatedAt(),
//                output.deletedAt()
//        );
//    }
}
