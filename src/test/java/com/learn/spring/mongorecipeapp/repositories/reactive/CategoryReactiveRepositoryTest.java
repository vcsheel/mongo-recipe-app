package com.learn.spring.mongorecipeapp.repositories.reactive;

import com.learn.spring.mongorecipeapp.models.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest{

    public static final String INDIAN = "Indian";
    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category cat = new Category();
        cat.setDescription(INDIAN);
        categoryReactiveRepository.save(cat).block();
        Long count = categoryReactiveRepository.count().block();

        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findByDescription() {
        Category cat = new Category();
        cat.setDescription(INDIAN);
        categoryReactiveRepository.save(cat).block();

        Category savedCat = categoryReactiveRepository.findByDescription(INDIAN).block();

        assertNotNull(savedCat.getId());
        assertEquals(INDIAN, savedCat.getDescription());
    }
}