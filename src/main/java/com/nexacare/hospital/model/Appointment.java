package com.nexacare.hospital.model;

import com.nexacare.hospital.enums.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private Doctor doctor;
    @Column(nullable = false)
    private LocalDate appointmentDate;
    @Column(nullable = false)
    private LocalTime appointmentTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Reason reason;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus appointmentStatus;
    @Column(nullable = false)
    private  String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String doctorNotes;

    private Double height;
    private Double weight;
    @Column(length = 20)
    private String bloodPressure;
    @Column(nullable = false)
    private Double consultationFee;
    @Column(nullable = false)
    private Double medicineFee;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    @CreationTimestamp
    private Instant createdAt;

}
