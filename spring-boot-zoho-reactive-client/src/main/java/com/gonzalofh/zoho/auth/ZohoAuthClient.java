package com.gonzalofh.zoho.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
public class ZohoAuthClient {

  private final WebClient zohoAuthWebClient;
  private final ZohoAuthProperties properties;

  private static final Pattern SUCCESS_PATTERN =
      Pattern.compile("AUTHTOKEN=(\\w{32})\nRESULT=TRUE$");

  public String getAuthToken() {
    return zohoAuthWebClient.post()
        .uri(UriComponentsBuilder
            .fromUriString(properties.getUri())
            .queryParam("SCOPE", properties.getScope())
            .queryParam("EMAIL_ID", properties.getUser())
            .queryParam("PASSWORD", properties.getPwd())
            .toUriString())
        .retrieve()
        .bodyToMono(String.class)
        .map(SUCCESS_PATTERN::matcher)
        .filter(Matcher::find)
        .map(matcher -> matcher.group(1))
        .block();
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class ZohoAuthProperties {

    private String user;
    private String uri;
    private String pwd;
    private String scope;

  }

}
