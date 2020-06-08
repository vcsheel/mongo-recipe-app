package com.learn.spring.mongorecipeapp.services;

import com.learn.spring.mongorecipeapp.commands.RecipeCommand;
import com.learn.spring.mongorecipeapp.converters.RecipeCommandToRecipe;
import com.learn.spring.mongorecipeapp.models.Recipe;
import com.learn.spring.mongorecipeapp.exceptions.NotFoundException;
import com.learn.spring.mongorecipeapp.converters.RecipeToRecipeCommand;
import com.learn.spring.mongorecipeapp.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {

        Recipe recipe = recipeRepository.findById(id).block();

        if (recipe == null) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id );
        }

        return Mono.just(recipe);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id).block());

        //enhance command object with id value
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(rc -> {
                rc.setRecipeId(recipeCommand.getId());
            });
        }

        return Mono.just(recipeCommand);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return recipeRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public Mono<Void> deleteById(String idToDelete) {
        recipeRepository.deleteById(idToDelete);
        return Mono.empty();
    }
}
