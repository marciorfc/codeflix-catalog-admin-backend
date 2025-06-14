package org.codeflix.admin.catalogo.application.category.create;

import org.codeflix.admin.catalogo.domain.category.Category;
import org.codeflix.admin.catalogo.domain.category.CategoryGateway;
import org.codeflix.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(new ThrowsValidationHandler());

        final var createdCategory = this.categoryGateway.create(aCategory);

        return CreateCategoryOutput.from(createdCategory);
    }
}
