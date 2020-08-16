package com.gonzalofh.zoho.people.timetracker.client;

import com.gonzalo.zoho.domain.client.ZohoResponse;
import com.gonzalo.zoho.domain.client.people.timetracker.AddTimeLog;
import com.gonzalo.zoho.domain.client.people.timetracker.TimeLogAdded;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class AddTimeLogReactiveClient implements AddTimeLog<Flux<TimeLogAdded>> {

  private final WebClient zohoWebClient;
  private final String uri;

  public Flux<TimeLogAdded> addTimeLog(Request request) {
    return zohoWebClient.post()
        .uri(UriComponentsBuilder
            .fromUriString(uri)
            .queryParam("workDate", request.getWorkDate())
            .queryParam("jobId", request.getJobId())
            .queryParam("hours", request.getHours())
            .queryParam("billingStatus", request.getBillingStatus())
            .queryParam("user", request.getUser())
            .queryParam("authtoken", request.getAuthtoken())
            .toUriString())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(new ParameterizedTypeReference<ZohoResponse<TimeLogAdded>>() {
        })
        .map(ZohoResponse::getResponse)
        .flatMapIterable(ZohoResponse.Response::getResult);
  }

}
