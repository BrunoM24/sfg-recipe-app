package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.UnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasureCommand convert(UnitOfMeasure source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(source.getId());
        command.setDescription(source.getDescription());

        return command;
    }

}
