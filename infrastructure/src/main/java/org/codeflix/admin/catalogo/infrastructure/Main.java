package org.codeflix.admin.catalogo.infrastructure;

import org.codeflix.admin.catalogo.application.UseCase;
import org.codeflix.admin.catalogo.domain.category.Category;
import org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryJpaEntity;
import org.codeflix.admin.catalogo.infrastructure.category.persistence.CategoryRepository;
import org.codeflix.admin.catalogo.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
        System.out.println("Hello and welcome!");
       // System.out.println(new UseCase().execute());
    }

//    @Bean
//    public ApplicationRunner runner(CategoryRepository categoryRepository) {
//        return args -> {
//            List<CategoryJpaEntity> all = categoryRepository.findAll();
//
//            Category filmes = Category.newCategory("Filmes", null, true);
//
//            categoryRepository.saveAndFlush(CategoryJpaEntity.from(filmes));
//
//            categoryRepository.deleteAll();
//
//        };
//    }
}