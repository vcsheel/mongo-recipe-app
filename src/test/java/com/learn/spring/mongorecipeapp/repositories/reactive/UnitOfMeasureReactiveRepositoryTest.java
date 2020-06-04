package com.learn.spring.mongorecipeapp.repositories.reactive;

import com.learn.spring.mongorecipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryTest {

    public static final String TEASPOON = "Teaspoon";
    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(TEASPOON);
        unitOfMeasureReactiveRepository.save(uom).block();
        Long count = unitOfMeasureReactiveRepository.count().block();
        assertEquals(Long.valueOf(1L), count);
    }

    @Test
    public void findByDescription() {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(TEASPOON);
        unitOfMeasureReactiveRepository.save(uom).block();

        UnitOfMeasure savedUOM = unitOfMeasureReactiveRepository.findByDescription(TEASPOON).block();
        assertNotNull(savedUOM.getId());
        assertEquals(TEASPOON, savedUOM.getDescription());
    }
}