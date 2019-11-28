package com.brunom24.sfgrecipeapp.repositories;

import com.brunom24.sfgrecipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
