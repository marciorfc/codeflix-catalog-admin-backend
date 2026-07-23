package org.codeflix.admin.catalogo.infrastructure.api.controllers;

import org.codeflix.admin.catalogo.application.category.create.CreateCategoryCommand;
import org.codeflix.admin.catalogo.application.category.create.CreateCategoryOutput;
import org.codeflix.admin.catalogo.application.category.create.CreateCategoryUseCase;
import org.codeflix.admin.catalogo.application.category.retrieve.get.GetCategoryByIdUseCase;
import org.codeflix.admin.catalogo.domain.pagination.Pagination;
import org.codeflix.admin.catalogo.domain.validation.handler.NotificationHandler;
import org.codeflix.admin.catalogo.infrastructure.category.models.CreateCategoryApiInput;
import org.codeflix.admin.catalogo.infrastructure.category.models.GetCategoryApiOutput;
import org.codeflix.admin.catalogo.infrastructure.category.presenters.CategoryApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.function.Function;

@RestController
public class CategoryController implements CategoryAPI {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoryByIdUseCase getCategoryByIdUseCase;

    public CategoryController(
            final CreateCategoryUseCase createCategoryUseCase,
            final GetCategoryByIdUseCase getCategoryByIdUseCase
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoryByIdUseCase = getCategoryByIdUseCase;
    }

    @Override
    public ResponseEntity<?> createCategory(final CreateCategoryApiInput input) {
        final var aCommand = CreateCategoryCommand.with(
                input.name(),
                input.description(),
                input.active() != null ? input.active() : true
        );

        final Function<NotificationHandler, ResponseEntity<?>> onError = notificationHandler ->
            ResponseEntity.unprocessableEntity().body(notificationHandler);

        final Function<CreateCategoryOutput, ResponseEntity<?>> onSuccess = output ->
                ResponseEntity.created(URI.create("/categories/" + output.id())).body(output);

        return this.createCategoryUseCase.execute(aCommand)
                .fold(onError, onSuccess);

    }

    @Override
    public Pagination<?> listCategories(String search, int page, int perPage, String sort, String direction) {
        return null;
    }

    @Override
    public GetCategoryApiOutput getById(String id) {
        return CategoryApiPresenter.present.apply(this.getCategoryByIdUseCase.execute(id));
    }


}
