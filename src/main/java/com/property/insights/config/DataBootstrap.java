package com.property.insights.config;

import com.property.insights.model.Property;
import com.property.insights.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataBootstrap implements CommandLineRunner {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public void run(String... args) throws Exception {
        // Initialize mock data only if database is empty
        if (propertyRepository.count() == 0) {
            List<Property> mockProperties = Arrays.asList(
                new Property(null, "123 Maple St, Downtown", 2500, 95, 95, "Apartment", null, null),
                new Property(null, "456 Oak Ave, Suburbs", 3200, 100, 100, "House", null, null),
                new Property(null, "789 Pine Rd, City Center", 1800, 85, 85, "Studio", null, null),
                new Property(null, "321 Elm Blvd, Eastside", 2800, 90, 95, "Apartment", null, null),
                new Property(null, "654 Birch Ln, Westend", 4100, 100, 100, "House", null, null),
                new Property(null, "987 Cedar Dr, Northgate", 2200, 80, 80, "Apartment", null, null)
            );
            propertyRepository.saveAll(mockProperties);
            System.out.println("✅ Mock data initialized: " + mockProperties.size() + " properties");
        } else {
            System.out.println("✅ Database already contains " + propertyRepository.count() + " properties");
        }
    }
}