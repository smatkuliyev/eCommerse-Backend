package com.edu.ecom.controller;

import com.edu.ecom.common.ApiResponse;
import com.edu.ecom.model.Category;
import com.edu.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "a new category created"), HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> createCategory() {
        return categoryService.listCategory();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        System.out.println("Category id " + categoryId);
        if (!categoryService.findById(categoryId)){
            return new ResponseEntity<>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<>(new ApiResponse(true, "category has been updated"), HttpStatus.OK);
    }
}
