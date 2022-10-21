package com.asg.services.apple;

/*-
 * #%L
 * apple-inf
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

import com.asg.services.common.api.AsgService;
import com.asg.services.apple.pojos.Apple;
import com.asg.services.apple.pojos.AppleCreateRequest;
import com.asg.services.apple.pojos.AppleUpdateRequest;

import java.util.List;


@AsgService
public interface AppleService {
    Apple createApple(AppleCreateRequest pojo);
    boolean deleteApple(String appleId);
    List<Apple> getAllApples();
    Apple getApple(String appleId);
    Apple updateApple(String appleId, AppleUpdateRequest applePojoUpdateRequest);
}
