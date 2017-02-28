package com.vetty.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.vetty.model.Attendance;
import com.vetty.model.Veterinary;
import com.vetty.repository.AttendanceRepository;
import com.vetty.repository.VeterinaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping("/attendances")
    public List getAttendances() {
        return attendanceRepository.findAll();
    }

    @PostMapping(value = "/attendance")
    public ResponseEntity create(@Valid @RequestBody Attendance attendance) {
        attendanceRepository.create(attendance);
        return new ResponseEntity(attendance, HttpStatus.OK);
    }
}
