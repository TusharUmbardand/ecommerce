package com.ecommerce.service;

import com.ecommerce.model.Category;

import java.util.List;

public interface CategoryService {
    public void createCategory(Category category);

    public List<Category> getAllCategories();

    public String deleteCategory(Long id);

    String updateCategory(Category category
    );
}
