package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.CategoryCommand;
import com.brunom24.sfgrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Override
    @Nullable
    @Synchronized
    public Category convert(CategoryCommand source) {
        if (source == null) {
            return null;
        }

        Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());

        return category;
    }

}
