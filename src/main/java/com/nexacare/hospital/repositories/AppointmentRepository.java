package com.nexacare.hospital.repositories;

import com.nexacare.hospital.model.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.doctor.user.username = ?1
            ORDER BY a.appointmentDate DESC
            """)
    List<Appointment> findByDoctorUserUsername(String username, Pageable pageable);

    @Query("""
            SELECT a
            FROM Appointment a
            WHERE a.patient.user.username = ?1
            ORDER BY a.appointmentDate DESC
            """)
    List<Appointment> findByPatientUserUsername(String username, Pageable pageable);
}