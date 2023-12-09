package org.sonthai.sleep_tracker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.sonthai.sleep_tracker.entity.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {

  Optional<List<Sleep>> getSleepsByUsername(String username);

  Optional<Sleep> getSleepByIdAndUsername(Long id, String username);

  Optional<List<Sleep>> getSleepsByDateBetweenAndUsernameOrderByDateAsc(LocalDate startDate, LocalDate endDate, String username);
}
