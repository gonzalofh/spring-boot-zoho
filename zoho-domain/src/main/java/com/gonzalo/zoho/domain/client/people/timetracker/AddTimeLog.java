package com.gonzalo.zoho.domain.client.people.timetracker;

import lombok.Getter;

public interface AddTimeLog<T> {

  T addTimeLog(Request request);

  @Getter
  class Request extends BaseRequest {

    private final String workDate;
    private final String toDate;
    private final String billingStatus;
    private final String jobId;
    private final int hours;

    public Request(String user, String authtoken, String workDate, String toDate,
        String billingStatus, String jobId, int hours) {
      super(user, authtoken);
      this.workDate = workDate;
      this.toDate = toDate;
      this.billingStatus = billingStatus;
      this.jobId = jobId;
      this.hours = hours;

    }

  }

}
