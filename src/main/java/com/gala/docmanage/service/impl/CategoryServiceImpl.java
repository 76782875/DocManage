package com.gala.docmanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gala.docmanage.dao.CategoryDAO;
import com.gala.docmanage.entity.Category;
import com.gala.docmanage.modules.constant.DefaultValues;
import com.gala.docmanage.service.ICategoryService;
import com.zhazhapan.util.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDAO, Category> implements ICategoryService  {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {this.categoryDAO = categoryDAO;}

    @Override
    public boolean insert(String name,int code) {
        return Checker.isNotNull(code) && Checker.isNotNull(name) && categoryDAO.insertCategory(name,code);
    }

    @Override
    public boolean remove(int id) {
        return isCategorized(id) && categoryDAO.removeCategoryById(id);
    }

    @Override
    public boolean update(int id, String name,int code) {
        return Checker.isNotEmpty(name) && isCategorized(id) && categoryDAO.updateNameById(id, name,code);
    }

    private boolean isCategorized(int id) {
        return !DefaultValues.UNCATEGORIZED.equals(getById(id).getName());
    }

    @Override
    public Category getById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Override
    public List<Category> list() {
        return categoryDAO.listCategory();
    }

    @Override
    public int getIdByName(String name) {
        try {
            return categoryDAO.getIdByName(name);
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }
}
