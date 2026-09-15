package com.finbank.medicineservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "category-service")
public interface CategoryClient {

    @GetMapping("/api/categories/{id}")
    ResponseEntity<Map<String, Object>> getCategoryById(@PathVariable("id") Long id);
}
