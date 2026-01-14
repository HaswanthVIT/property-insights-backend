package com.property.insights.controller;

import com.property.insights.model.Property;
import com.property.insights.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin(origins = "*")
public class PropertyController {

    @Autowired
    private PropertyRepository propertyRepository;

    // GET all properties
    @GetMapping
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    // GET property by ID
    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable Long id) {
        return propertyRepository.findById(id)
                .map(property -> ResponseEntity.ok(property))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Create new property
    @PostMapping
    public ResponseEntity<Property> createProperty(@RequestBody Property property) {
        // Calculate performance score based on rent and occupancy
        int score = calculatePerformanceScore((double) property.getRent(), (double) property.getOccupancy());
        property.setPerformanceScore(score);
        
        Property savedProperty = propertyRepository.save(property);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProperty);
    }

    // PUT - Update existing property
    @PutMapping("/{id}")
    public ResponseEntity<Property> updateProperty(@PathVariable Long id, @RequestBody Property propertyDetails) {
        return propertyRepository.findById(id)
                .map(property -> {
                    property.setAddress(propertyDetails.getAddress());
                    property.setPropertyType(propertyDetails.getPropertyType());
                    property.setRent(propertyDetails.getRent());
                    property.setOccupancy(propertyDetails.getOccupancy());
                    
                    // Recalculate performance score
                    int score = calculatePerformanceScore((double) propertyDetails.getRent(), (double) propertyDetails.getOccupancy());
                    property.setPerformanceScore(score);
                    
                    Property updatedProperty = propertyRepository.save(property);
                    return ResponseEntity.ok(updatedProperty);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Remove property
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProperty(@PathVariable Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
                    propertyRepository.delete(property);
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "Property deleted successfully");
                    response.put("id", id.toString());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // GET analytics
    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics() {
        List<Property> properties = propertyRepository.findAll();
        
        if (properties.isEmpty()) {
            Map<String, Object> emptyAnalytics = new HashMap<>();
            emptyAnalytics.put("totalRevenue", 0);
            emptyAnalytics.put("averageOccupancy", 0.0);
            emptyAnalytics.put("averageScore", 0.0);
            emptyAnalytics.put("totalProperties", 0);
            emptyAnalytics.put("averageRent", 0.0);
            return ResponseEntity.ok(emptyAnalytics);
        }
        
        int totalRevenue = properties.stream()
                .filter(p -> p.getOccupancy() == 100)
                .mapToInt(Property::getRent)
                .sum();
        
        double avgOccupancy = properties.stream()
                .mapToInt(Property::getOccupancy)
                .average()
                .orElse(0.0);
        
        double avgScore = properties.stream()
                .mapToInt(Property::getPerformanceScore)
                .average()
                .orElse(0.0);
        
        double avgRent = properties.stream()
                .mapToInt(Property::getRent)
                .average()
                .orElse(0.0);
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalRevenue", totalRevenue);
        analytics.put("averageOccupancy", avgOccupancy);
        analytics.put("averageScore", avgScore);
        analytics.put("totalProperties", properties.size());
        analytics.put("averageRent", avgRent);
        
        return ResponseEntity.ok(analytics);
    }

    // Helper method to calculate performance score
    private int calculatePerformanceScore(double rent, double occupancy) {
        // Simple formula: base score from occupancy + bonus for high rent
        int baseScore = (int) occupancy; // 0-100
        int rentBonus = rent > 3000 ? 10 : (rent > 2500 ? 5 : 0);
        return Math.min(100, baseScore + rentBonus);
    }
}