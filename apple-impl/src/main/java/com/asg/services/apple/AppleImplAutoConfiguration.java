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

import com.asg.services.common.security.SecurityContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("'${asg.apple.type}'=='embedded' or '${asg.apple.type:restserver}'=='restserver'")
public class AppleImplAutoConfiguration {

    @Bean
    @ConfigurationProperties("asg.apple")
    public AppleServiceProperties appleServiceProperties() {
        return new AppleServiceProperties();
    }

    @Bean
    public AppleService appleService(SecurityContext securityContext, AppleServiceProperties properties) {
        return new AppleServiceImpl(securityContext, properties);
    }
}
