package com.accountingapi.service;

import com.accountingapi.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();

    Category findCategoryById(Long id);

    boolean existsById(Long id);

}
