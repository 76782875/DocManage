package com.gala.docmanage.web.controller;

import com.gala.docmanage.enums.InterceptorLevel;
import com.gala.docmanage.annotation.AuthInterceptor;
import com.gala.docmanage.entity.Category;
import com.gala.docmanage.service.ICategoryService;
import com.gala.docmanage.util.ControllerUtils;
import com.zhazhapan.modules.constant.ValueConsts;
import com.zhazhapan.util.Checker;
import com.zhazhapan.util.Formatter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/category")
@Api(value = "/category", description = "文件项目相关操作")
public class CategoryController {

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {this.categoryService = categoryService;}

    @ApiOperation(value = "新增一个项目")
    @AuthInterceptor(InterceptorLevel.ADMIN)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(String name,int code) {
        return ControllerUtils.getResponse(categoryService.insert(name,code));
    }

    @ApiOperation(value = "更新项目名称")
    @ApiImplicitParam(name = "name", value = "新的名称", required = true)
    @AuthInterceptor(InterceptorLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String update(@PathVariable("id") int id, String name,int code) {
        boolean isSuccess = Checker.isNotEmpty(name) && categoryService.update(id, name,code);
        return ControllerUtils.getResponse(isSuccess);
    }

    @ApiOperation(value = "删除一个项目")
    @AuthInterceptor(InterceptorLevel.ADMIN)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String remove(@PathVariable("id") int id) {
        return ControllerUtils.getResponse(categoryService.remove(id));
    }

    @ApiOperation(value = "获取一个项目")
    @AuthInterceptor(InterceptorLevel.NONE)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable("id") int id) {
        Category category = categoryService.getById(id);
        if (Checker.isNull(category)) {
            return ControllerUtils.getResponse(ValueConsts.FALSE);
        } else {
            return category.toString();
        }
    }

    @ApiOperation(value = "获取所有项目")
    @AuthInterceptor(InterceptorLevel.NONE)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAll() {
        return Formatter.listToJson(categoryService.list());
    }
}
