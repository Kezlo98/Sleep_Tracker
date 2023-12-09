package org.sonthai.sleep_tracker.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.sonthai.sleep_tracker.entity.Sleep;
import org.sonthai.sleep_tracker.exception.DomainCode;
import org.sonthai.sleep_tracker.exception.DomainException;
import org.sonthai.sleep_tracker.mapper.SleepMapper;
import org.sonthai.sleep_tracker.model.dto.SleepDto;
import org.sonthai.sleep_tracker.model.dto.UserDto;
import org.sonthai.sleep_tracker.model.dto.WeekAverage;
import org.sonthai.sleep_tracker.repository.SleepRepository;
import org.sonthai.sleep_tracker.service.SleepService;
import org.sonthai.sleep_tracker.util.UserUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SleepServiceImpl implements SleepService {

  private SleepRepository sleepRepository;
  private SleepMapper sleepMapper;

  @Override
  public List<SleepDto> getSleeps () {
    UserDto userDto = UserUtils.getUserDtoFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Optional<List<Sleep>> optionalSleeps = sleepRepository.getSleepsByUsername(userDto.getUsername());
    if(optionalSleeps.isEmpty()) {
      return new ArrayList<>();
    }
    List<Sleep> sleeps = optionalSleeps.get();
    return sleepMapper.toDtos(sleeps);
  }

  @Override
  public SleepDto getSleep(Long id){
    Sleep sleep = getSleepByIdAndUsername(id);
    return sleepMapper.toDto(sleep);
  }

  @Override
  public SleepDto createSleep (SleepDto sleepDto){
    UserDto userDto = UserUtils.getUserDtoFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
    sleepDto.setUsername(userDto.getUsername());
    Sleep sleep = sleepMapper.toEntity(sleepDto);
    sleep = sleepRepository.save(sleep);
    return sleepMapper.toDto(sleep);
  }

  @Override
  public SleepDto updateSleep (Long id, SleepDto sleepDto){
    Sleep sleep = getSleepByIdAndUsername(id);
    sleepMapper.update(sleep,sleepDto);
    sleep = sleepRepository.save(sleep);
    return sleepMapper.toDto(sleep);
  }

  @Override
  public void deleteSleep (Long id){
    getSleepByIdAndUsername(id);
    sleepRepository.deleteById(id);
  }

  @Override
  public WeekAverage getAverageSleep (int weekPlus){
    LocalDate localDate = LocalDate.now().plusWeeks(weekPlus);
    TemporalField field = WeekFields.of(Locale.getDefault()).dayOfWeek();
    LocalDate startOfWeek = localDate.with(field, 1).plusDays(1);
    LocalDate endOfWeek = localDate.with(field, 7).plusDays(1);
    UserDto userDto = UserUtils.getUserDtoFromAuthentication(SecurityContextHolder.getContext().getAuthentication());

    Optional<List<Sleep>> optionalSleeps = sleepRepository.getSleepsByDateBetweenAndUsernameOrderByDateAsc(startOfWeek, endOfWeek, userDto.getUsername());
    if (optionalSleeps.isEmpty()) {
      return new WeekAverage();
    }
    List<Sleep> sleeps = optionalSleeps.get();
    Map<LocalDate, Sleep> sleepMap = new HashMap<>();
    int totalDate = 0;
    for (Sleep sleep : sleeps) {
      if(sleepMap.containsKey(sleep.getDate())) {
        sleepMap.put(sleep.getDate(), plusTimeForSleep(sleepMap.get(sleep.getDate()),sleep));
      } else {
        totalDate ++;
        sleepMap.put(sleep.getDate(), sleep);
      }
    }

    long nanoSleepTime = 0;
    long nanoWakeTime = 0;
    long nanoDuration = 0;
    for(Map.Entry<LocalDate, Sleep> entry : sleepMap.entrySet()) {
      nanoSleepTime += (entry.getValue().getSleepTime().toNanoOfDay())/totalDate;
      nanoWakeTime += (entry.getValue().getWakeTime().toNanoOfDay())/totalDate;
      nanoDuration += (entry.getValue().getDuration().toNanoOfDay())/totalDate;
    }

    return new WeekAverage(startOfWeek, endOfWeek, totalDate, LocalTime.ofNanoOfDay(nanoSleepTime), LocalTime.ofNanoOfDay(nanoWakeTime), LocalTime.ofNanoOfDay(nanoDuration));
  }

  private Sleep getSleepByIdAndUsername(Long id) {
    UserDto userDto = UserUtils.getUserDtoFromAuthentication(SecurityContextHolder.getContext().getAuthentication());
    Optional<Sleep> optionalSleep = sleepRepository.getSleepByIdAndUsername(id, userDto.getUsername());
    if(optionalSleep.isEmpty()) {
      throw new DomainException(DomainCode.SLEEP_IS_NOT_EXISTED,new Object[]{id});
    }
    return optionalSleep.get();
  }

  private Sleep plusTimeForSleep(Sleep source, Sleep target){
    source.setSleepTime(plus(source.getSleepTime(), target.getSleepTime()));
    source.setWakeTime(plus(source.getWakeTime(), target.getWakeTime()));
    source.setDuration(plus(source.getDuration(), target.getDuration()));
    return source;
  }

  private LocalTime plus(LocalTime first, LocalTime second) {
    return first.plusHours(second.getHour()).plusMinutes(second.getMinute()).plusSeconds(second.getSecond());
  }

}
