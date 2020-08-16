package com.gonzalofh.zoho.autoconfigure.auth;

import static org.assertj.core.api.Assertions.assertThat;

import com.gonzalofh.zoho.autoconfigure.client.ZohoAuthClientAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class ZohoAuthClientAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ZohoAuthClientAutoConfiguration.class));

    @Test
    void authClientIsCreated() {
        this.contextRunner
            .withPropertyValues(
                "zoho.auth.uri=localhost:8080/test",
                "zoho.auth.user=test",
                "zoho.auth.pwd=test",
                "zoho.auth.scope=test")
            .run((context) -> assertThat(context)
                .hasBean("zohoAuthClient"));
    }

}
