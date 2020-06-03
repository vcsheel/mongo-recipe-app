package com.learn.spring.mongorecipeapp.services;

import com.learn.spring.mongorecipeapp.commands.RecipeCommand;
import com.learn.spring.mongorecipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeCommand findCommandById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(String idToDelete);
}
