package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.RecipeCommand;
import com.brunom24.sfgrecipeapp.converters.RecipeCommandToRecipe;
import com.brunom24.sfgrecipeapp.converters.RecipeToRecipeCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.exceptions.NotFoundException;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;
    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);

        if (!optionalRecipe.isPresent()) {
            throw new NotFoundException("Recipe Not Found!");
        }

        return optionalRecipe.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    public void deleteByd(Long id) {
        recipeRepository.deleteById(id);
    }

}
