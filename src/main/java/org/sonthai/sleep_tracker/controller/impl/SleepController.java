package org.sonthai.sleep_tracker.controller.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.sonthai.sleep_tracker.controller.SleepOperations;
import org.sonthai.sleep_tracker.model.dto.SleepDto;
import org.sonthai.sleep_tracker.model.dto.WeekAverage;
import org.sonthai.sleep_tracker.service.SleepService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sleeps")
@AllArgsConstructor
public class SleepController implements SleepOperations {

  private final SleepService sleepService;

  @GetMapping
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public List<SleepDto> getSleeps() {
    return sleepService.getSleeps();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public SleepDto getSleep(@PathVariable Long id) {
    return sleepService.getSleep(id);
  }

  @PostMapping
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public SleepDto createSleep(@RequestBody SleepDto sleepDto) {
    return sleepService.createSleep(sleepDto);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public SleepDto updateSleep(@PathVariable Long id, @RequestBody SleepDto sleepDto) {
    return sleepService.updateSleep(id, sleepDto);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void deleteSleep(@PathVariable Long id) {
    sleepService.deleteSleep(id);
  }

  @GetMapping("/average")
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public WeekAverage getAverageSleep(@RequestParam int weekPlus) {
    return sleepService.getAverageSleep(weekPlus);
  }
}
