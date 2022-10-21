package com.asg.services.apple.test;

/*-
 * #%L
 * apple-test
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

import com.asg.services.apple.EnableAppleService;
import com.asg.services.toolkit.EnableFlyway;
import com.asg.services.toolkit.test.EnableTestCore;
import com.asg.services.toolkit.test.EnableTestDatabase;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableTestCore
@EnableTestDatabase
@EnableFlyway
@EnableAppleService
public @interface EnableTestApple {}
