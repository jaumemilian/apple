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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.client.WebTarget;

@Configuration
@ConditionalOnProperty(name = "asg.apple.type", havingValue = "rest", matchIfMissing = true)
public class AppleRestClientAutoConfiguration {

    @Bean
    public AppleService appleClient(LoadBalancedClient<WebTarget> client) {
        return new AppleRestClient(client);
    }
}
