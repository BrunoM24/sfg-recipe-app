package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAllUomCommands();

}
