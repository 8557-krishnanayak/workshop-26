package com.godigit.taskAppivation.service;

import com.godigit.taskAppivation.dto.CategoryDto;
import com.godigit.taskAppivation.dto.TaskDto;
import com.godigit.taskAppivation.model.CategoryModal;
import com.godigit.taskAppivation.model.TaskModel;
import com.godigit.taskAppivation.repository.CategoriesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    ModelMapper modelMapper;

    public CategoryModal getByName(String name){
        return categoriesRepository.findByName(name);
    }

    public CategoryDto createCategory(String categoryName) {
        // Code to create a new category
        CategoryModal categoryBuild = CategoryModal.builder().name(categoryName).build();
        return convertEntityToDto(categoriesRepository.save(categoryBuild));
    }


    public void deleteCategory(long categoryId) {
        categoriesRepository.deleteById(categoryId);
    }


    public List<CategoryDto> getAllCategories() {
        // Code to retrieve all categories
        return categoriesRepository.findAll().stream().map(this::convertEntityToDto).toList();
    }


    public CategoryDto getCategoryById(Long categoryId) {
        // Code to retrieve a specific category by ID
        CategoryModal categoryModal = categoriesRepository.findById(categoryId).orElseThrow();
        return convertEntityToDto(categoryModal);
    }


    private CategoryDto convertEntityToDto(CategoryModal categoryModal) {
        return modelMapper.map(categoryModal, CategoryDto.class);
    }

    private CategoryModal convertEntityToDto(CategoryDto category) {
        return modelMapper.map(category, CategoryModal.class);
    }

}
