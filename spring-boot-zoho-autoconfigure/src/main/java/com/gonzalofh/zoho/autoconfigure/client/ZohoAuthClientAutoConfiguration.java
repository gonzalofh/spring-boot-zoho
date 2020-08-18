package com.gonzalofh.zoho.autoconfigure.client;

import com.gonzalofh.zoho.auth.ZohoAuthClient;
import com.gonzalofh.zoho.auth.ZohoAuthClient.ZohoAuthProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Zoho authorization client.
 *
 * @author Gonzalo Fernández
 */
@Configuration
public class ZohoAuthClientAutoConfiguration {

  @Bean
  @ConfigurationProperties("zoho.auth")
  public ZohoAuthProperties zohoAuthProperties() {
    return new ZohoAuthProperties();
  }

  @Bean
  public WebClient zohoAuthWebClient(final ZohoAuthProperties clientProperties) {
    return WebClient.builder()
        .baseUrl(clientProperties.getUri())
        .build();
  }

  @Bean
  public ZohoAuthClient zohoAuthClient(final ZohoAuthProperties clientProperties,
      final WebClient zohoAuthWebClient) {
    return new ZohoAuthClient(zohoAuthWebClient, clientProperties);
  }

}
