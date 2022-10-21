package com.asg.services.apple;

/*-
 * #%L
 * apple-impl
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
import com.asg.services.common.security.SecurityContext;
import com.asg.services.apple.pojos.Apple;
import com.asg.services.apple.pojos.AppleCreateRequest;
import com.asg.services.apple.pojos.AppleUpdateRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AppleServiceImpl implements AppleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final SecurityContext securityContext;
    private final AppleServiceProperties properties;

    public AppleServiceImpl(SecurityContext securityContext, AppleServiceProperties properties){
        this.securityContext = securityContext;
        this.properties = properties;
    }

    @Override
    public Apple createApple(AppleCreateRequest pojo) {
        logger.trace("calling createApple");
        throw new NotImplementedException();
    }

    @Override
    public boolean deleteApple(String appleId) {
        logger.trace("calling deleteApple");
        throw new NotImplementedException();
    }

    @Override
    public List<Apple> getAllApples() {
        logger.trace("calling getAllApple");
        throw new NotImplementedException();
    }

    @Override
    public Apple getApple(String appleId) {
        logger.trace("calling getApple");
        throw new NotImplementedException();
    }

    @Override
    public Apple updateApple(String appleId, AppleUpdateRequest applePojoUpdateRequest) {
        logger.trace("calling updateApple");
        throw new NotImplementedException();
    }
}
