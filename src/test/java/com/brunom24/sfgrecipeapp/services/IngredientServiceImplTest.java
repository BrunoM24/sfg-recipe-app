package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.converters.IngredientToIngredientCommand;
import com.brunom24.sfgrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.domain.Ingredient;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    IngredientToIngredientCommand ingredientConverter;

    IngredientService ingredientService;

    public IngredientServiceImplTest() {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientConverter);
    }

    @Test
    public void findIngredientCommandByIdAndRecipeId() {
        //given
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        //when
        IngredientCommand ingredientCommand = ingredientService.findIngredientCommandByIdAndRecipeId(3L, 1L);

        //then
        assertEquals(Long.valueOf(3L), ingredientCommand.getId());
        assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
        verify(recipeRepository).findById(anyLong());

    }

}