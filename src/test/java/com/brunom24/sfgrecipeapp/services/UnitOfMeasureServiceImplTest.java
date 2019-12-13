package com.brunom24.sfgrecipeapp.services;

import com.brunom24.sfgrecipeapp.commands.UnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.brunom24.sfgrecipeapp.domain.UnitOfMeasure;
import com.brunom24.sfgrecipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    private UnitOfMeasureToUnitOfMeasureCommand commandConverter;

    public UnitOfMeasureServiceImplTest() {
        this.commandConverter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, commandConverter);
    }

    @Test
    public void findAllUomCommands() {
        //given
        Set<UnitOfMeasure> unitOfMeasureSet = new HashSet<>();

        UnitOfMeasure unit1 = new UnitOfMeasure();
        unit1.setId(1L);
        unitOfMeasureSet.add(unit1);

        UnitOfMeasure unit2 = new UnitOfMeasure();
        unit2.setId(2L);
        unitOfMeasureSet.add(unit2);

        //when
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasureSet);

        Set<UnitOfMeasureCommand> allUomCommands = service.findAllUomCommands();

        //then
        assertEquals(2, allUomCommands.size());
        verify(unitOfMeasureRepository).findAll();
    }
}