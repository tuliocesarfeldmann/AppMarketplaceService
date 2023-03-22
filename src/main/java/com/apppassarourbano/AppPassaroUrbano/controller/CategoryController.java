package com.apppassarourbano.AppPassaroUrbano.controller;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Category;
import com.apppassarourbano.AppPassaroUrbano.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController extends AppRestAbstract<Long, Category, CategoryService> {
}
