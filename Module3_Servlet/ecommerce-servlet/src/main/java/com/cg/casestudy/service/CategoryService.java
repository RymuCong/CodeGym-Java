package com.cg.casestudy.service;

import com.cg.casestudy.entity.Category;

import java.util.List;

public interface CategoryService {

    boolean saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(int cid);

    void deleteCategory(int cid);

    void updateCategory(Category category);

    String getCategoryNameById(int cid);

    Category getCategoryByName(String name);

    int getCategoryCount();

}
