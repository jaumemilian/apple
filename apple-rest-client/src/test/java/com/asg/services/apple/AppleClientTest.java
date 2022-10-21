package com.asg.services.apple;

/*-
 * #%L
 * apple-rest-client
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

import com.asg.services.common.exceptions.NotImplementedException;
import com.asg.services.apple.pojos.Apple;
import com.asg.services.toolkit.RestClientAutoConfiguration;
import com.asg.services.toolkit.cloud.ServicesDiscoveryAutoConfiguration;
import com.asg.services.toolkit.test.EnableTestCore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.asg.services.toolkit.cloud.SpringDiscoveryClients.http;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AppleClientTest {



    @Configuration
    @EnableTestCore
    @Import({RestClientAutoConfiguration.class,
            AppleRestClientAutoConfiguration.class,
            ServicesDiscoveryAutoConfiguration.class})
    public static class Config {

        @Bean
        public DiscoveryClient discoveryClient(WireMockServer server) {
            return http("apples", "localhost", server.port());
        }

        @Bean
        public WireMockServer wireMockServer() {
            WireMockServer server = new WireMockServer(wireMockConfig().dynamicPort());
            server.start();
            WireMock.configureFor("localhost", server.port());
            return server;
        }
    }
    
    @Autowired
    AppleService service;


    @Test
    public void testGetApple() throws Exception{
        Apple mockedApple = new Apple();
        mockedApple.setId("123");
        mockedApple.setName("myName");

        stubFor(get(urlPathEqualTo("/rest/apple/apples/123"))
                .withHeader("Accept", equalTo("application/vnd.asg-services-apple.v1+json"))
                .willReturn(okJson(
                        new ObjectMapper().writeValueAsString(mockedApple))));
        Apple rtnApple = service.getApple("123");

        assertEquals(mockedApple.getId(),rtnApple.getId());
        assertEquals(mockedApple.getName(),rtnApple.getName());

    }
}
