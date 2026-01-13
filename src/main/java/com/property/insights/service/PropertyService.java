package com.property.insights.service;

import com.property.insights.model.Property;
import com.property.insights.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PropertyService {
    
    @Autowired
    private PropertyRepository propertyRepository;
    
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
    
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).orElse(null);
    }
    
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }
    
    public void initializeMockData() {
        if (propertyRepository.count() == 0) {
            List<Property> mockProperties = Arrays.asList(
                new Property(null, "123 Maple St, Downtown", 2500.0, 95, 87, "Apartment", 2, null, null),
                new Property(null, "456 Oak Ave, Suburbs", 3200.0, 100, 92, "House", 3, null, null),
                new Property(null, "789 Pine Rd, City Center", 1800.0, 85, 78, "Studio", 1, null, null),
                new Property(null, "321 Elm Blvd, Eastside", 2800.0, 90, 85, "Apartment", 2, null, null),
                new Property(null, "654 Birch Ln, Westend", 4100.0, 100, 95, "House", 4, null, null),
                new Property(null, "987 Cedar Dr, Northgate", 2200.0, 80, 72, "Apartment", 1, null, null)
            );
            propertyRepository.saveAll(mockProperties);
        }
    }
    
    public Map<String, Object> getAnalytics() {
        Map<String, Object> analytics = new HashMap<>();
        
        Double avgRent = propertyRepository.findAverageRent();
        Double avgOccupancy = propertyRepository.findAverageOccupancy();
        Double avgScore = propertyRepository.findAverageScore();
        
        analytics.put("totalProperties", propertyRepository.count());
        analytics.put("averageRent", avgRent != null ? avgRent : 0.0);
        analytics.put("averageOccupancy", avgOccupancy != null ? avgOccupancy : 0.0);
        analytics.put("averageScore", avgScore != null ? avgScore : 0.0);
        
        List<Property> allProps = propertyRepository.findAll();
        Double totalRevenue = allProps.stream().mapToDouble(Property::getRent).sum();
        analytics.put("totalRevenue", totalRevenue);
        
        return analytics;
    }
}