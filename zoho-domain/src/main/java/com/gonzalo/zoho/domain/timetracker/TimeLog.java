package com.gonzalo.zoho.domain.timetracker;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class TimeLog {

  private final String erecno;
  private final boolean timerLog;
  private final String employeeMailId;
  private final boolean isTimelogPushedToZF;
  private final int jobColor;
  private final String employeeFirstName;
  private final boolean isDeleteAllowed;
  private final String type;
  private final String workDate;
  private final String billedStatus;
  private final boolean jobIsActive;
  private final String jobName;
  private final String approvalStatus;
  private final String hours;
  private final String clientId;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.S")
  private final LocalDate db_workDate;
  private final int jobIsCompleted;
  private final String jobBillableStatus;
  private final int hoursInMins;
  private final boolean isEditAllowed;
  private final String billingStatus;
  private final String jobId;
  private final boolean isTimelogPushedToQBO;
  private final int totaltime;
  private final String employeeLastName;
  private final String timelogId;
  private final int tt_inputType;
  private final String projectName;
  private final String projectId;
  private final boolean isPushAllowToZF;

}