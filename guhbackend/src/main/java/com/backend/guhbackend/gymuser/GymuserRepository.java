package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.gymuser.Gymuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GymuserRepository extends JpaRepository<Gymuser, Long> {
    @Query("SELECT g FROM Gymuser g WHERE g.email =?1")
    Optional<Gymuser> findGymuserByEmail(String email);
}
