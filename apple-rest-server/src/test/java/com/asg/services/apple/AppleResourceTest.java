package com.asg.services.apple;

/*-
 * #%L
 * apple-rest-server
 * %%
 * Copyright (C) 2003 - 2021 ASG Technologies Group
 * %%
 * NOTICE:  All information contained herein is, and remains
 * the property of ASG Technologies Group, Inc. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to ASG Technologies Group, Inc.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from ASG Technologies Group, Inc.
 * #L%
 */

import com.asg.services.toolkit.test.EnableTestCore;
import com.asg.services.toolkit.test.SpringJerseyTest;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppleResourceTest extends SpringJerseyTest {

    @Configuration
    @EnableTestCore
    public static class Config {

        @Bean
        public AppleService appleService() {
            return mock(AppleService.class);
        }
    }

    @Autowired
    AppleService service;

    @Override
    protected ResourceConfig init() {
        return new ResourceConfig(AppleResource.class);
    }

    @Test
    public void deleteProtocolTest() {
        when(service.deleteApple(anyString())).thenReturn(true);
        final Response response = target("/apple/apples").path("123")
                .request()
                .delete();
        assertThat(response.getStatus(), is(Response.Status.NO_CONTENT.getStatusCode()));
    }
}
