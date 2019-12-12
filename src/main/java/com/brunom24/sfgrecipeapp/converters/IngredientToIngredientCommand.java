package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.IngredientCommand;
import com.brunom24.sfgrecipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.uomConverter = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    @Nullable
    @Synchronized
    public IngredientCommand convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        command.setAmount(ingredient.getAmount());
        command.setDescription(ingredient.getDescription());

        if (ingredient.getUnitMeasure() != null) {
            command.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitMeasure()));
        }

        return command;
    }

}
