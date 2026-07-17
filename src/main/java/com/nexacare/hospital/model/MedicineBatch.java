package com.nexacare.hospital.model;

import com.nexacare.hospital.enums.BatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "medicine_batch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private String batchNo;

    private Integer quantityReceived;

    private Integer quantityRemaining;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private BatchStatus batchStatus;

    @CreationTimestamp
    private Instant createdAt;
}