package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.NotesCommand;
import com.brunom24.sfgrecipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

    @Override
    @Nullable
    @Synchronized
    public Notes convert(NotesCommand command) {
        if (command == null) {
            return null;
        }

        Notes notes = new Notes();
        notes.setId(command.getId());
        notes.setRecipeNotes(command.getRecipeNotes());

        return notes;
    }

}
