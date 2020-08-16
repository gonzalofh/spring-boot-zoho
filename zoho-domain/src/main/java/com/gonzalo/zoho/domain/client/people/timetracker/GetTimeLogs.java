package com.gonzalo.zoho.domain.client.people.timetracker;

import lombok.Getter;

public interface GetTimeLogs<T> {

  T getTimeLogs(final Request request);

  @Getter
  class Request extends BaseRequest {

    private final String fromDate;
    private final String toDate;
    private final String billingStatus;
    private final String jobId;

    public Request(String user, String authtoken, String fromDate, String toDate,
        String billingStatus, String jobId) {
      super(user, authtoken);
      this.fromDate = fromDate;
      this.toDate = toDate;
      this.billingStatus = billingStatus;
      this.jobId = jobId;
    }
  }

}
