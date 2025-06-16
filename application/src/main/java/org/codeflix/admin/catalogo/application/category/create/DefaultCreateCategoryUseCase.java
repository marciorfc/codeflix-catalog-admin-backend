package org.codeflix.admin.catalogo.application.category.create;

import io.vavr.API;
import io.vavr.control.Either;
import org.codeflix.admin.catalogo.domain.category.Category;
import org.codeflix.admin.catalogo.domain.category.CategoryGateway;
import org.codeflix.admin.catalogo.domain.validation.handler.NotificationHandler;
import org.codeflix.admin.catalogo.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<NotificationHandler, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var notification = NotificationHandler.create();

        final var aCategory = Category.newCategory(aName, aDescription, isActive);
        aCategory.validate(notification);

       

        //final var createdCategory = this.categoryGateway.create(aCategory);

        //return CreateCategoryOutput.from(createdCategory);
        
        return notification.hasError() ? API.Left(notification) : create(aCategory);
    }

    private Either<NotificationHandler, CreateCategoryOutput> create(Category aCategory) {

        return API.Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(NotificationHandler::create, CreateCategoryOutput::from);

    }
}
