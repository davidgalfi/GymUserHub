package com.backend.guhbackend.gymworker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GymworkerRepository extends JpaRepository<Gymworker, Long> {

    @Query("SELECT w FROM Gymworker w WHERE w.email = ?1")
    Optional<Gymworker> findGymworkerByEmail(String email);


    @Query("SELECT w FROM Gymworker w WHERE w.phone =?1")
    Optional<Gymworker> findGymworkerByPhone(String phone);
}
