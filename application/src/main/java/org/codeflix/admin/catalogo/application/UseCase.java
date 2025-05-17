package org.codeflix.admin.catalogo.application;

import org.codeflix.admin.catalogo.domain.category.Category;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class UseCase {

    public Category execute() {
        return Category.newCategory("cat 1", "desc cat 1", true);
    }
}