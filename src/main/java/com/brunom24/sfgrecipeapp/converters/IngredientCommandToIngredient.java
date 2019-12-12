package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom;

    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom) {
        this.uomCommandToUom = uomCommandToUom;
    }

    @Override
    @Nullable
    @Synchronized
    public Ingredient convert(IngredientCommand command) {
        if (command == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(command.getId());
        ingredient.setDescription(command.getDescription());
        ingredient.setAmount(command.getAmount());

        if (command.getUnitOfMeasure() != null) {
            ingredient.setUnitMeasure(uomCommandToUom.convert(command.getUnitOfMeasure()));
        }

        return ingredient;
    }

}
