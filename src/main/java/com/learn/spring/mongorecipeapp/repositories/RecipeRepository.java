package com.learn.spring.mongorecipeapp.repositories;

import com.learn.spring.mongorecipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeRepository extends CrudRepository<Recipe, String> {
}
