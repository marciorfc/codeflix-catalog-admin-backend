package org.codeflix.admin.catalogo.application.category.create;

import io.vavr.control.Either;
import org.codeflix.admin.catalogo.application.UseCase;
import org.codeflix.admin.catalogo.domain.validation.handler.NotificationHandler;

public abstract class CreateCategoryUseCase extends UseCase<CreateCategoryCommand, Either<NotificationHandler, CreateCategoryOutput>> {



}
