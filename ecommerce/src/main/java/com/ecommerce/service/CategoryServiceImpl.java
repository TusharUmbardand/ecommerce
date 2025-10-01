package com.ecommerce.service;

import com.ecommerce.exception.APIException;
import com.ecommerce.exception.ResourceNotFound;
import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public void createCategory(Category category) {
        Category category1 = categoryRepository.findByCategoryName(category.getCategoryName());
        if (category1==null){
            categoryRepository.save(category);
            return;
        }
        throw new APIException("Category with the name "+ category.getCategoryName()+" is already present");
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()){
            throw  new APIException("No categories are created till now");
        }
        return categories;
    }

    @Override
    public String deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("NOT Found","NOT Found",id));

        categoryRepository.deleteById(id);
        return "Category with id : " + id + " is deleted";
    }

    @Override
    public String updateCategory(Category category) {
        Category categoryTobeUpdated = categoryRepository.findById(category.getCategoryId()).orElseThrow(() -> new ResourceNotFound("NOT Found","NOT Found",category.getCategoryId())) ;
        categoryTobeUpdated.setCategoryName(category.getCategoryName());
        categoryRepository.save(categoryTobeUpdated);
        System.out.println(category);

        return "Category with id : " + category.getCategoryId() + " is Updated";
    }

}
