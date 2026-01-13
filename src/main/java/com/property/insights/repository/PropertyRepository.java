package com.property.insights.repository;

import com.property.insights.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    @Query("SELECT AVG(p.rent) FROM Property p")
    Double findAverageRent();
    
    @Query("SELECT AVG(p.occupancy) FROM Property p")
    Double findAverageOccupancy();
    
    @Query("SELECT AVG(p.performanceScore) FROM Property p")
    Double findAverageScore();
    
    List<Property> findByPerformanceScoreGreaterThan(Integer score);
    
    @Query("SELECT p.propertyType, COUNT(p) FROM Property p GROUP BY p.propertyType")
    List<Object[]> countByPropertyType();
}