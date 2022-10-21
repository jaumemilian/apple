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

import com.asg.services.apple.api.AppleTagApi;
import com.asg.services.apple.dto.Apple;
import com.asg.services.apple.dto.AppleCreateRequest;
import com.asg.services.apple.dto.AppleList;
import com.asg.services.apple.dto.AppleUpdateRequest;
import com.asg.services.toolkit.UserResource;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.CREATED;
import static javax.ws.rs.core.Response.Status.OK;

@UserResource(service="apple")
public class AppleResource implements AppleTagApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ModelMapper mapper;
    @Autowired
    @SuppressWarnings("SpringJavaAutowiredMembersInspection")
    private AppleService service;

    public AppleResource() {
        this.mapper = new ModelMapper();
    }


    @Override
    public Response createApple(AppleCreateRequest body) {
        logger.trace("Calling {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        // Convert from the DTO to the POJO
        com.asg.services.apple.pojos.AppleCreateRequest pojo = mapper.map(body,com.asg.services.apple.pojos.AppleCreateRequest.class );
        // Call the service
        com.asg.services.apple.pojos.Apple applePojo = service.createApple(pojo);
        // Convert from the POJO to DTO
        Apple apple = mapper.map(applePojo, Apple.class);
        // Return the result
        logger.trace("Leaving {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return Response.status(CREATED).entity(apple).build();
    }

    @Override
    public Response deleteApple(String appleId) {
        logger.trace("Calling {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        // Call the service to delete the apple
        service.deleteApple(appleId);
        // If there was a problem, an execption would have been thrown and caught by the Jersey Exception Mapper, and return the proper HTTP code.
        logger.trace("Leaving {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return Response.noContent().build();
    }

    @Override
    public Response getAllApples() {
        logger.trace("Calling {}", Thread.currentThread().getStackTrace()[1].getMethodName());

        // Call the service to get the list of Apples
        List<com.asg.services.apple.pojos.Apple> applePojoList= service.getAllApples();

        // iterate through the pojo list, convert them via the mapper and get the proper DTO formatted object list
        List<Apple> listOfApples =  applePojoList.stream().map(applePojo -> mapper.map(applePojo,Apple.class)).collect(Collectors.toList());
        AppleList appleList = new AppleList();
        // Add the list to the returned array
        appleList.addAll(listOfApples);
        logger.trace("Leaving {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        return Response.status(OK)
                .entity(appleList)
                .build();
    }

    @Override
    public Response getApple(String appleId) {
        logger.trace("Calling {}", Thread.currentThread().getStackTrace()[1].getMethodName());

        // Call the service to get the Apple
        com.asg.services.apple.pojos.Apple applePojo = service.getApple(appleId);
        // Convert POJO to DTO
        Apple apple = mapper.map(applePojo, Apple.class);
        logger.trace("Leaving {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        // Return the Apple
        return Response.status(OK)
                .entity(apple)
                .build();
    }

    @Override
    public Response updateApple(String appleId, AppleUpdateRequest body) {
        logger.trace("Calling {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        // Convert from DTO to POJO
        com.asg.services.apple.pojos.AppleUpdateRequest applePojoUpdateRequest = mapper.map(body,com.asg.services.apple.pojos.AppleUpdateRequest.class);
        // Call the service
        com.asg.services.apple.pojos.Apple updatedPojo = service.updateApple(appleId, applePojoUpdateRequest);
        // Convert POJO to DTO
        Apple apple = mapper.map(updatedPojo, Apple.class);

        logger.trace("Leaving {}", Thread.currentThread().getStackTrace()[1].getMethodName());
        // Return the Apple
        return Response.status(OK)
                .entity(apple)
                .build();
    }
}
