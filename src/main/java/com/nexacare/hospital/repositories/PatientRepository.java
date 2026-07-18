package com.nexacare.hospital.repositories;

import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;



import java.util.Optional;

@Repository
public interface PatientRepository  extends JpaRepository<Patient,Long> {

    Optional<Patient> findByUserId(Long id);

    Optional<Patient> findByUser(User user);
}
