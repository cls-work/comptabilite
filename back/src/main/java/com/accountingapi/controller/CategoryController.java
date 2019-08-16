package com.accountingapi.controller;

import com.accountingapi.model.Category;
import com.accountingapi.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;


    // -------------------Retrieve All Categories---------------------------------------------

    @GetMapping
    public ResponseEntity<?> findAllCategories() {
        List<Category> categories = categoryService.findAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);

    }

    // -------------------Retrieve One Category By ID---------------------------------------------

    @GetMapping("/{categoryId}")
    public ResponseEntity<?> findCategoryById(@PathVariable("categoryId") Long categoryId) {
        if (categoryService.existsById(categoryId))
            return new ResponseEntity<>(categoryService.findCategoryById(categoryId), HttpStatus.OK);
        else return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
    }
}
