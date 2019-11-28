package com.brunom24.sfgrecipeapp.repositories;

import com.brunom24.sfgrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
