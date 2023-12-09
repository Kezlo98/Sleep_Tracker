package org.sonthai.sleep_tracker.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "sleep")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Sleep {

  @Id
  @GeneratedValue
  private Long id;
  private String username;
  private LocalDate date;
  private LocalTime sleepTime;
  private LocalTime wakeTime;
  private LocalTime duration;
}
