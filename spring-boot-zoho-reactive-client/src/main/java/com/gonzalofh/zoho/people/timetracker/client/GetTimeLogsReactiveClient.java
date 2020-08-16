package com.gonzalofh.zoho.people.timetracker.client;

import com.gonzalo.zoho.domain.client.ZohoResponse;
import com.gonzalo.zoho.domain.client.people.timetracker.GetTimeLogs;
import com.gonzalo.zoho.domain.timetracker.TimeLog;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetTimeLogsReactiveClient implements GetTimeLogs<Flux<TimeLog>> {

  private final WebClient zohoWebClient;
  private final String uri;

  public Flux<TimeLog> getTimeLogs(final Request request) {
    return zohoWebClient.get()
        .uri(UriComponentsBuilder
            .fromUriString(uri)
            .queryParam("fromDate", request.getFromDate())
            .queryParam("toDate", request.getToDate())
            .queryParam("billingStatus", request.getBillingStatus())
            .queryParam("jobId", request.getJobId())
            .queryParam("user", request.getUser())
            .queryParam("authtoken", request.getAuthtoken())
            .toUriString())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToFlux(new ParameterizedTypeReference<ZohoResponse<TimeLog>>() {
        })
        .map(ZohoResponse::getResponse)
        .flatMapIterable(ZohoResponse.Response::getResult);
  }

}
