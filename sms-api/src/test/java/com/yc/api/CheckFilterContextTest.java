package com.yc.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CheckFilterContextTest {


    @Autowired
    private CheckFilterContext checkFilterContext;

    @Test
    public void check() {
        checkFilterContext.check(new Object());
    }
}