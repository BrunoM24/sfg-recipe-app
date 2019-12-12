package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.RecipeCommand;
import com.brunom24.sfgrecipeapp.converters.RecipeCommandToRecipe;
import com.brunom24.sfgrecipeapp.converters.RecipeToRecipeCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RecipeServiceIT {

    private static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Test
    @Transactional
    public void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe recipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(recipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(recipe.getId(), savedRecipeCommand.getId());
        assertEquals(recipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(recipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }

}