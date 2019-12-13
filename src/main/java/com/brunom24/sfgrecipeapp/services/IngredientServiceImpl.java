package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.converters.IngredientCommandToIngredient;
import com.brunom24.sfgrecipeapp.converters.IngredientToIngredientCommand;
import com.brunom24.sfgrecipeapp.domain.Ingredient;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import com.brunom24.sfgrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository uomRepository;
    private final IngredientToIngredientCommand ingredientConverter;
    private final IngredientCommandToIngredient commandConverter;

    public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository uomRepository, IngredientToIngredientCommand ingredientConverter, IngredientCommandToIngredient commandConverter) {
        this.recipeRepository = recipeRepository;
        this.uomRepository = uomRepository;
        this.ingredientConverter = ingredientConverter;
        this.commandConverter = commandConverter;
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

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(ingredientCommand.getRecipeId());

        if (!optionalRecipe.isPresent()){
            log.error("Recipe id not found for id: " + ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }

        Recipe recipe = optionalRecipe.get();

        Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                .findFirst();

        if (optionalIngredient.isPresent()){
            Ingredient ingredient = optionalIngredient.get();

            ingredient.setDescription(ingredientCommand.getDescription());
            ingredient.setAmount(ingredientCommand.getAmount());
            ingredient.setUnitMeasure(uomRepository.findById(ingredientCommand.getUnitOfMeasure().getId())
                    .orElseThrow(() -> new RuntimeException("Unit Of Measure not found")));
        } else {
            recipe.addIngredient(commandConverter.convert(ingredientCommand));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        return ingredientConverter.convert(
                savedRecipe.getIngredients().stream()
                        .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                        .findFirst().get());
    }

}
