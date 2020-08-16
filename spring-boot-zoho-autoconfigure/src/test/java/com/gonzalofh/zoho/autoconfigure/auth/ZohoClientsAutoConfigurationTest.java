package com.gonzalofh.zoho.autoconfigure.auth;

import static org.assertj.core.api.Assertions.assertThat;

import com.gonzalofh.zoho.autoconfigure.client.ZohoClientsAutoConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class ZohoClientsAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ZohoClientsAutoConfiguration.class));

    @Test
    void clientsAreCreated() {
        this.contextRunner
            .withPropertyValues(
                "zoho.people.host=localhost:8080")
            .run((context) -> assertThat(context)
                .hasBean("addTimeLog")
                .hasBean("getTimeLogs"));
    }

}
