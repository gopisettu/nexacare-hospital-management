package com.nexacare.hospital.repositories;

import com.nexacare.hospital.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine,Long> {
}
