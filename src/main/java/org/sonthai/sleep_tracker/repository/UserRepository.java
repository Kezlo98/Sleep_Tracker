package org.sonthai.sleep_tracker.repository;

import org.sonthai.sleep_tracker.constant.RegistrationEnum;
import org.sonthai.sleep_tracker.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = (:username) AND u.registrationId = (:registrationId)")
    Optional<User> findUserByUsernameAndRegistrationId(String username, RegistrationEnum registrationId);
}
