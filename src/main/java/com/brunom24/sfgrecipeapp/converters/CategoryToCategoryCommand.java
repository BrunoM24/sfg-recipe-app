package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.CategoryCommand;
import com.brunom24.sfgrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Override
    @Nullable
    @Synchronized
    public CategoryCommand convert(Category category) {
        if (category == null){
            return null;
        }

        CategoryCommand command = new CategoryCommand();
        command.setId(category.getId());
        command.setDescription(category.getDescription());

        return command;
    }

}
