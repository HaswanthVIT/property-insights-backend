package com.property.insights.controller;

import com.property.insights.model.Property;
import com.property.insights.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;
    
    @PostConstruct
    public void init() {
        propertyService.initializeMockData();
    }
    
    @GetMapping
    public ResponseEntity<List<Property>> getAllProperties() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        Property property = propertyService.getPropertyById(id);
        return property != null ? ResponseEntity.ok(property) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        return ResponseEntity.ok(propertyService.createProperty(property));
    }
    
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        return ResponseEntity.ok(propertyService.getAnalytics());
    }
}