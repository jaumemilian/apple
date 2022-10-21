package com.asg.services.apple;

import com.asg.services.common.exceptions.NotImplementedException;
import com.asg.services.toolkit.test.EnableTestCore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is an example of a unit test for IMPL.
 * Be sure to test positive and negative use cases.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AppleImplTest {

    @Configuration
    @EnableTestCore
    @Import(AppleImplAutoConfiguration.class)
    public static class Config {
    }

    @Autowired
    AppleService service;

    @Test (expected = NotImplementedException.class)
    public void testGetApple(){
        service.getApple("123");
    }
}
