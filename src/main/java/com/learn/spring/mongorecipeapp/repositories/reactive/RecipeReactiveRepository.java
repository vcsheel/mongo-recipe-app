package com.learn.spring.mongorecipeapp.repositories.reactive;

import com.learn.spring.mongorecipeapp.models.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeReactiveRepository extends ReactiveMongoRepository<Recipe, String> {
}
