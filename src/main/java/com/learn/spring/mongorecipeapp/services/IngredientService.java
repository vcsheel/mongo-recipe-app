package com.learn.spring.mongorecipeapp.services;

import com.learn.spring.mongorecipeapp.commands.IngredientCommand;

/**
 * Created by jt on 6/27/17.
 */
public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteById(String recipeId, String idToDelete);
}
