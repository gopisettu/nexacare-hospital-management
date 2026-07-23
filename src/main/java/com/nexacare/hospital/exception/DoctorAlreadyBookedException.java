package com.nexacare.hospital.exception;

public class DoctorAlreadyBookedException extends RuntimeException {
    public DoctorAlreadyBookedException(String message) {
        super(message);
    }
}
