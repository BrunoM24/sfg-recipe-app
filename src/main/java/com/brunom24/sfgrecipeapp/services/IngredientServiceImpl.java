package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.converters.IngredientToIngredientCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientConverter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientConverter) {
        this.recipeRepository = recipeRepository;
        this.ingredientConverter = ingredientConverter;
    }

    @Override
    public IngredientCommand findIngredientCommandByIdAndRecipeId(Long ingredientId, Long recipeId) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

        if (!optionalRecipe.isPresent()) {
            log.error("Recipe not found for id: " + recipeId);
        }

        Recipe recipe = optionalRecipe.get();

        Optional<IngredientCommand> optionalIngredientCommand = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredientConverter::convert).findFirst();

        if (!optionalIngredientCommand.isPresent()) {
            log.error("Ingredient not found for id: " + ingredientId);
        }

        return optionalIngredientCommand.get();
    }

}
