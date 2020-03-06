package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.converters.RecipeCommandToRecipe;
import com.brunom24.sfgrecipeapp.converters.RecipeToRecipeCommand;
import com.brunom24.sfgrecipeapp.domain.Recipe;
import com.brunom24.sfgrecipeapp.exceptions.NotFoundException;
import com.brunom24.sfgrecipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    public void getAllRecipes() {

        Recipe recipe = new Recipe();
        HashSet<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getAllRecipes();

        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeByIdTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Optional<Recipe> optionalRecipe = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null Recipe Returned", recipeReturned);
        verify(recipeRepository).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdNotFound() {
        Optional<Recipe> optionalRecipe = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);

        Recipe recipeReturned = recipeService.findById(1L);
    }

    @Test
    public void testDeleteById() {
        //given
        Long idToDelete = 1L;

        //when
        recipeService.deleteByd(idToDelete);

        //then
        verify(recipeRepository).deleteById(anyLong());
    }

}