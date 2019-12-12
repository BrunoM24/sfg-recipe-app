package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.UnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    @Nullable
    @Synchronized
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null){
            return null;
        }

        final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(source.getId());
        unitOfMeasure.setDescription(source.getDescription());

        return unitOfMeasure;
    }

}
