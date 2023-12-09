package org.sonthai.sleep_tracker.service;

import java.util.List;
import org.sonthai.sleep_tracker.model.dto.SleepDto;
import org.sonthai.sleep_tracker.model.dto.WeekAverage;

public interface SleepService {

  List<SleepDto> getSleeps ();

  SleepDto getSleep (Long id);

  SleepDto createSleep (SleepDto sleepDto);

  SleepDto updateSleep (Long id, SleepDto sleepDto);

  void deleteSleep (Long id);

  WeekAverage getAverageSleep (int weekPlus);
}
