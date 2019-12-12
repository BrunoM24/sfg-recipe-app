package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.RecipeCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long id);

    void deleteByd(Long id);

}
