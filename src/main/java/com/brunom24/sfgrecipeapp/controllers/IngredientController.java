package com.brunom24.sfgrecipeapp.controllers;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.services.IngredientService;
import com.brunom24.sfgrecipeapp.services.RecipeService;
import com.brunom24.sfgrecipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    private final UnitOfMeasureService uomService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService uomService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.uomService = uomService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model){

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findIngredientCommandByIdAndRecipeId(ingredientId, recipeId));

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateForm(@PathVariable Long recipeId, @PathVariable Long ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findIngredientCommandByIdAndRecipeId(ingredientId, recipeId));

        model.addAttribute("uomList", uomService.findAllUomCommands());

        return "recipe/ingredient/ingredientForm";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand){

        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(ingredientCommand);

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

}
