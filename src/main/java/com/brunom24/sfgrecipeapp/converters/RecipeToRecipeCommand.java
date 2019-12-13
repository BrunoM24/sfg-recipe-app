package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.RecipeCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand categoryConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryConverter, IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter) {
        this.categoryConverter = categoryConverter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setDescription(recipe.getDescription());
        command.setCookTime(recipe.getCookTime());
        command.setDifficulty(recipe.getDifficulty());
        command.setDirections(recipe.getDirections());
        command.setPrepTime(recipe.getPrepTime());
        command.setServings(recipe.getServings());
        command.setSource(recipe.getSource());
        command.setUrl(recipe.getUrl());
        command.setNotes(notesConverter.convert(recipe.getNotes()));

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories().forEach(category -> command.getCategories().add(categoryConverter.convert(category)));
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients().forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }

}
