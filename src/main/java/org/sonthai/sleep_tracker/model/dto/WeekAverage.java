package org.sonthai.sleep_tracker.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeekAverage {

  private LocalDate fromDate;
  private LocalDate toDate;
  private int dateCount;
  private LocalTime averageSleepTime;
  private LocalTime averageWakeTime;
  private LocalTime averageDuration;

}
