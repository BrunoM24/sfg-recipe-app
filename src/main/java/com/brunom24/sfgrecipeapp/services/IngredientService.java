package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findIngredientCommandByIdAndRecipeId(Long ingredientId, Long recipeId);

}
