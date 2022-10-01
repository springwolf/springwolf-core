package io.github.stavshamir.springwolf.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class SpringwolfExampleApplicationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void testContext() {
        assertNotNull(context);
    }
}