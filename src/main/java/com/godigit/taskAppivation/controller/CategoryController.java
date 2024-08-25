package com.godigit.taskAppivation.controller;


import com.godigit.taskAppivation.dto.CategoryDto;
import com.godigit.taskAppivation.model.CategoryModal;
import com.godigit.taskAppivation.repository.CategoriesRepository;
import com.godigit.taskAppivation.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoriesService categoriesService;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody CategoryDto categoryModal) {
        return new ResponseEntity<>(categoriesService.createCategory(categoryModal.getName()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(categoriesService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoriesService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id) {
        categoriesService.deleteCategory(id);
        return ResponseEntity.ok("category with id " + id + " has been deleted!");
    }
}
