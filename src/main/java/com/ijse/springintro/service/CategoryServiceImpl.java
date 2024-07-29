package com.ijse.springintro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ijse.springintro.entity.Category;
import com.ijse.springintro.repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository CategoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return CategoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        return CategoryRepository.save(category);
    }

}
