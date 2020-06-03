package com.learn.spring.mongorecipeapp.services;

import com.learn.spring.mongorecipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
