package org.codeflix.admin.catalogo.application.category.update;

import io.vavr.control.Either;
import org.codeflix.admin.catalogo.application.UseCase;
import org.codeflix.admin.catalogo.domain.validation.handler.NotificationHandler;

public abstract class UpdateCategoryUseCase extends UseCase<UpdateCategoryCommand, Either<NotificationHandler, UpdateCategoryOutput>> {

}

