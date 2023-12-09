package org.sonthai.sleep_tracker.mapper;

import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.sonthai.sleep_tracker.entity.Sleep;
import org.sonthai.sleep_tracker.model.dto.SleepDto;

@Mapper(componentModel = "spring")
public interface SleepMapper {

  SleepDto toDto(Sleep sleep);

  @Mapping(target = "id", ignore = true)
  Sleep toEntity(SleepDto sleepDto);

  List<SleepDto> toDtos(List<Sleep> sleeps);

  @Mapping(target = "id", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
  void update(@MappingTarget Sleep user, SleepDto userDto);

}
