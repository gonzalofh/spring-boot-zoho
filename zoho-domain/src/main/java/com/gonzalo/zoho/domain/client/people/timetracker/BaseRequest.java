package com.gonzalo.zoho.domain.client.people.timetracker;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BaseRequest {

  protected final String user;
  protected final String authtoken;

}
