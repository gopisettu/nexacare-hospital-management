package com.nexacare.hospital.model;

import com.nexacare.hospital.enums.Symptom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "appointment_symptom")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentSymptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Enumerated(EnumType.STRING)
    private Symptom symptom;
}
