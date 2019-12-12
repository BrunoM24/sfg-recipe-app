package com.brunom24.sfgrecipeapp.converters;

import com.brunom24.sfgrecipeapp.commands.NotesCommand;
import com.brunom24.sfgrecipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Override
    @Nullable
    @Synchronized
    public NotesCommand convert(Notes notes) {
        if (notes == null) {
            return null;
        }

        NotesCommand command = new NotesCommand();
        command.setId(notes.getId());
        command.setRecipeNotes(notes.getRecipeNotes());

        return command;
    }

}
