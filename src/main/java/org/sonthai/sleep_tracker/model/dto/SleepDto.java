package org.sonthai.sleep_tracker.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

@Data
public class SleepDto {

  private Long id;
  private String username;
  @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd")
  private String date;
  @JsonFormat(shape = Shape.STRING,pattern = "HH:mm:ss")
  private String sleepTime;
  @JsonFormat(shape = Shape.STRING,pattern = "HH:mm:ss")
  private String wakeTime;
  @JsonFormat(shape = Shape.STRING,pattern = "HH:mm:ss")
  private String duration;

}
