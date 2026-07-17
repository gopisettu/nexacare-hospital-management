package com.nexacare.hospital.repositories;

import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long> {
    @Query("""
            Select u
                from User u
                    where u.username=?1
            """)
    Optional<User> findByUser(String username);
}
