package org.codeflix.admin.catalogo.infrastructure.category.persistence;

import org.codeflix.admin.catalogo.domain.category.Category;
import org.codeflix.admin.catalogo.infrastructure.MySQLGatewayTest;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Testar todos os campos Not Null da tabela Category
 */
@MySQLGatewayTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void givenAnInvalidNullName_whenCallsSave_shouldReturnError() {
        final var expectedMessage = "not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.name; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.name";
        final var expectedPropertyName = "name";
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setName(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.save(anEntity));

        final var actualCause =
            Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidNullCreatedAt_whenCallsSave_shouldReturnError() {
        final var expectedMessage = "not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.createdAt; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.createdAt";
        final var expectedPropertyName = "createdAt";
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setCreatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.save(anEntity));

        final var actualCause =
                Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidNullUpdatedAt_whenCallsSave_shouldReturnError() {
        final var expectedMessage = "not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.updatedAt; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity.updatedAt";
        final var expectedPropertyName = "updatedAt";
        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setUpdatedAt(null);

        final var actualException = Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> categoryRepository.save(anEntity));

        final var actualCause =
                Assertions.assertInstanceOf(PropertyValueException.class, actualException.getCause());

        Assertions.assertEquals(expectedPropertyName, actualCause.getPropertyName());
        Assertions.assertEquals(expectedMessage, actualException.getMessage());
    }
}
