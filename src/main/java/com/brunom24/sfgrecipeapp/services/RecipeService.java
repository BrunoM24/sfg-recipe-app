package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

}
