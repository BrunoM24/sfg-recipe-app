package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.UnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand commandConverter;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand commandConverter) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.commandConverter = commandConverter;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAllUomCommands() {
        //Mine Implementation
        /*Set<UnitOfMeasureCommand> commandSet = new HashSet<>();

        unitOfMeasureRepository.findAll().forEach(
                unitOfMeasure -> commandSet.add(commandConverter.convert(unitOfMeasure))
        );

        return commandSet;*/

        //SFG Implementation
        return StreamSupport
                .stream(unitOfMeasureRepository.findAll().spliterator(),false)
                .map(commandConverter::convert)
                .collect(Collectors.toSet());
    }

}
