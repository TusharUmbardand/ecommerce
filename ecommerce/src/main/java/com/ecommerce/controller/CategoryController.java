package com.ecommerce.controller;

import com.ecommerce.exception.ResourceNotFound;
import com.ecommerce.model.Category;
import com.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("api/public/getCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category>categories=categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @PostMapping("api/admin/addCategory")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }

    @DeleteMapping("api/admin/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {

        String status = categoryService.deleteCategory(id);
        return new ResponseEntity<>(status,HttpStatus.OK);

    }

    @PutMapping("api/admin/updateCategory")
    public ResponseEntity<String> updateCategory(@RequestBody Category category ) {

            String status = categoryService.updateCategory(category);
            return new ResponseEntity<>(status,HttpStatus.OK);

    }
}
