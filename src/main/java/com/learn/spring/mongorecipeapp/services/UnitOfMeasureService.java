package com.learn.spring.mongorecipeapp.services;

import com.learn.spring.mongorecipeapp.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
