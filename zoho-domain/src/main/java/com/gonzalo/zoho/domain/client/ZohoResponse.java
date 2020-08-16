package com.gonzalo.zoho.domain.client;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ZohoResponse<T> {

  private final Response<T> response;

  @Getter
  @RequiredArgsConstructor
  public static class Response<T> {

    private final List<T> result;

  }

}