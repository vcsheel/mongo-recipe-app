package com.learn.spring.mongorecipeapp.repositories;

import com.learn.spring.mongorecipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
