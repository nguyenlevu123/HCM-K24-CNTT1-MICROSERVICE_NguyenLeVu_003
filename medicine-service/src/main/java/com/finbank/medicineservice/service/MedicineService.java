package com.finbank.medicineservice.service;

import com.finbank.medicineservice.client.CategoryClient;
import com.finbank.medicineservice.entity.Medicine;
import com.finbank.medicineservice.repository.MedicineRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepository medicineRepository;
    private final CategoryClient categoryClient;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public ResponseEntity<?> createMedicine(Medicine medicine) {
        if (medicine.getCategoryId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", 404, "message", "Category not found"));
        }

        try {
            ResponseEntity<Map<String, Object>> response = categoryClient.getCategoryById(medicine.getCategoryId());
            if (response == null || !response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("status", 404, "message", "Category not found"));
            }
        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", 404, "message", "Category not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("status", 404, "message", "Category not found"));
        }

        Medicine saved = medicineRepository.save(medicine);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
