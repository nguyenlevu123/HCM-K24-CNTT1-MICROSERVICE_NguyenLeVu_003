package com.finbank.medicineservice.controller;

import com.finbank.medicineservice.entity.Medicine;
import com.finbank.medicineservice.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(medicineService.getAllMedicines());
    }

    @PostMapping
    public ResponseEntity<?> createMedicine(@RequestBody Medicine medicine) {
        return medicineService.createMedicine(medicine);
    }
}
