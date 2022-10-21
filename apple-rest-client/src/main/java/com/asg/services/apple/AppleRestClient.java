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

import com.asg.services.common.discovery.LoadBalancedClient;
import com.asg.services.common.exceptions.InvalidRequestException;
import com.asg.services.apple.pojos.Apple;
import com.asg.services.apple.pojos.AppleCreateRequest;
import com.asg.services.apple.pojos.AppleUpdateRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static com.asg.services.common.client.ResponseUtils.convertToException;
import static javax.ws.rs.client.Entity.entity;
import static javax.ws.rs.core.Response.Status.*;

public class AppleRestClient implements AppleService {

    private final LoadBalancedClient<WebTarget> client;
    private final ModelMapper mapper = new ModelMapper();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AppleRestClient(LoadBalancedClient<WebTarget> client) {
        this.client = client;
    }

    private WebTarget next() {
        return client.next("apples").path("rest").path("apple");
    }


    @Override
    public Apple createApple(AppleCreateRequest pojo) {
        logger.debug("Calling createApple( {} )", pojo);
        // Convert the POJO to a DTO
        com.asg.services.apple.dto.AppleCreateRequest dto = mapper.map(pojo, com.asg.services.apple.dto.AppleCreateRequest.class);

        // Issue the REST request
        Response response = next().path("apples")
                .request()
                .accept("application/vnd.asg-services-apple-create-request.v1+json")
                .post(entity(dto, "application/vnd.asg-services-apple.v1+json"));

        if (response.getStatus() == CREATED.getStatusCode()) {
            // Convert the DTO response to a POJO
            return mapper.map(response.readEntity( com.asg.services.apple.dto.Apple.class), Apple.class);
        }

        logger.warn("CreateApple call failed");
        // If creation, then translate the HTTP response codes to the proper Exception
        throw convertToException(response);
    }

    @Override
    public boolean deleteApple(String appleId) {
        logger.debug("Calling deleteApple( {} )", appleId);
        if (StringUtils.isEmpty(appleId)) {
            throw new InvalidRequestException("Apple id cannot be empty");
        }
        final Response response = next().path("apples").path(appleId)
                .request()
                .delete();
        if (response.getStatus() == NO_CONTENT.getStatusCode()) {
            return true;
        }
        // If deletion failed, then translate the HTTP response codes to the proper Exception
        logger.warn("DeleteApple call failed");
        throw convertToException(response);
    }

    @Override
    public List<Apple> getAllApples() {
        logger.debug("Calling getAllApples");
        // Issue the REST request
        Response response = next().path("apples")
                .request()
                .accept("application/vnd.asg-services-apple-list.v1+json")
                .get();

        if (response.getStatus() == OK.getStatusCode()) {
            // get the dto List from the response
            com.asg.services.apple.dto.AppleList dtoList = response.readEntity(com.asg.services.apple.dto.AppleList.class);
            // Iterate through the DTO list and convert to POJO list
            List<Apple> pojoList = new ArrayList<>();
            dtoList.forEach(dto -> {
                pojoList.add(mapper.map(dto,Apple.class));
            });
            // return POJO list
            return pojoList;
        }
        // If the retrieval failed,  then translate the HTTP response codes to the proper Exception
        logger.warn("GetAllApple call failed");
        throw convertToException(response);
    }

    @Override
    public Apple getApple(String appleId) {
        logger.debug("Calling getApple( {} )", appleId);
        // Issue the REST request
        Response response = next().path("apples").path(appleId)
                .request()
                .accept("application/vnd.asg-services-apple.v1+json")
                .get();

        if (response.getStatus() == OK.getStatusCode()) {
            // Get dto from the response
            com.asg.services.apple.dto.Apple dto = response.readEntity(com.asg.services.apple.dto.Apple.class);
            // Convert DTO to POJO
            Apple pojo = mapper.map(dto,Apple.class);
            // Return the POJO
            return pojo;
        }
        // If the retrieval failed,  then translate the HTTP response codes to the proper Exception
        logger.warn("GetApple call failed");
        throw convertToException(response);
    }

    @Override
    public Apple updateApple(String appleId, AppleUpdateRequest applePojoUpdateRequest) {
        logger.debug("Calling updateApple( ID: {}, REQ: {} )", appleId, applePojoUpdateRequest);
        // Convert the POJO to DTO
        com.asg.services.apple.dto.AppleUpdateRequest dtoUpdateRequest = mapper.map(applePojoUpdateRequest,com.asg.services.apple.dto.AppleUpdateRequest.class);

        // Issue the REST request
        Response response = next().path("apples").path(appleId)
                .request()
                .accept("application/vnd.asg-services-apple.v1+json")
                .put(entity(dtoUpdateRequest,"application/vnd.asg-services-apple-update-request.v1+json"));

        if (response.getStatus() == OK.getStatusCode()) {
            // Get dto from the response
            com.asg.services.apple.dto.Apple dto = response.readEntity(com.asg.services.apple.dto.Apple.class);
            // Convert DTO to POJO
            Apple pojo = mapper.map(dto,Apple.class);
            // Return the POJO
            return pojo;
        }
        // If the update failed,  then translate the HTTP response codes to the proper Exception
        logger.warn("UpdateApple call failed");
        throw convertToException(response);    }
}
