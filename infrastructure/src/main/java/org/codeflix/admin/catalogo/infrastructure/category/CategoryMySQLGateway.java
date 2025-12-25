package org.codeflix.admin.catalogo.infrastructure.category;

import org.codeflix.admin.catalogo.domain.category.Category;
import org.codeflix.admin.catalogo.domain.category.CategoryGateway;
import org.codeflix.admin.catalogo.domain.category.CategoryID;
import org.codeflix.admin.catalogo.domain.category.CategorySearchQuery;
import org.codeflix.admin.catalogo.domain.pagination.Pagination;
import org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.codeflix.admin.catalogo.infrastructure.utils.SpecificationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Optional;

import static org.codeflix.admin.catalogo.infrastructure.utils.SpecificationUtils.like;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    public CategoryMySQLGateway(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Category create(final Category aCategory) {
        return this.save(aCategory);
    }

    @Override
    public void deleteById(CategoryID anId) {
        final String anIdValue = anId.getValue();
        if (this.categoryRepository.existsById(anIdValue)) {
            this.categoryRepository.deleteById(anIdValue);
        }
    }

    @Override
    public Category update(final Category aCategory) {
        return this.save(aCategory);
    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return this.categoryRepository.findById(anId.getValue())
                .map(CategoryJpaEntity::toAggregate);
    }

    @Override
    public Pagination<Category> findAll(CategorySearchQuery aQuery) {
        //Paginacao
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        //Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(str -> SpecificationUtils
                        .<CategoryJpaEntity>like("name", str)
                        .or(like("description", str))
                )
                .orElse(null);

        final var pageResult = this.categoryRepository.findAll(Specification.where(specifications), page);
        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(CategoryJpaEntity::toAggregate).toList()
        );
    }

    private Category save(final Category aCategory) {
        return this.categoryRepository.save(CategoryJpaEntity.from(aCategory))
                .toAggregate();
    }
}
