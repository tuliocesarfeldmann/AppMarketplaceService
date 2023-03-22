package com.apppassarourbano.AppPassaroUrbano.service;

import com.apppassarourbano.AppPassaroUrbano.model.entity.Category;
import com.apppassarourbano.AppPassaroUrbano.repository.category.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AppServiceAbstract<Long, Category, CategoryRepository> {
}
