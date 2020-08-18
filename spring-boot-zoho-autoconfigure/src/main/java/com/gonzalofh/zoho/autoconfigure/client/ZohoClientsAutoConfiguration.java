package com.gonzalofh.zoho.autoconfigure.client;

import com.gonzalo.zoho.domain.client.people.timetracker.AddTimeLog;
import com.gonzalo.zoho.domain.client.people.timetracker.GetTimeLogs;
import com.gonzalo.zoho.domain.client.people.timetracker.TimeLogAdded;
import com.gonzalo.zoho.domain.timetracker.TimeLog;
import com.gonzalofh.zoho.people.timetracker.client.AddTimeLogReactiveClient;
import com.gonzalofh.zoho.people.timetracker.client.GetTimeLogsReactiveClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Zoho clients.
 *
 * @author Gonzalo Fernández
 */
@Configuration
@ConditionalOnProperty("zoho.people.host")
public class ZohoClientsAutoConfiguration {

  @Value("${zoho.people.host}")
  private String host;

  @Bean
  public WebClient zohoWebClient() {
    return WebClient.builder()
        .baseUrl(host)
        .clientConnector(new ReactorClientHttpConnector(
            HttpClient.create()
                .followRedirect(true)
                .wiretap(true)))
        .build();
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(AddTimeLogReactiveClient.class)
  public AddTimeLog<Flux<TimeLogAdded>> addTimeLog(
      @Value("${zoho.people.timetracker.add-time-log.uri}") final String uri) {
    return new AddTimeLogReactiveClient(zohoWebClient(), uri);
  }

  @Bean
  @ConditionalOnMissingBean
  @ConditionalOnClass(GetTimeLogsReactiveClient.class)
  public GetTimeLogs<Flux<TimeLog>> getTimeLogs(
      @Value("${zoho.people.timetracker.get-log-times.uri}") final String uri) {
    return new GetTimeLogsReactiveClient(zohoWebClient(), uri);
  }

}
