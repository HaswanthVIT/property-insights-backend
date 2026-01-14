package com.property.insights.repository;

import com.property.insights.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    // JpaRepository provides all CRUD operations by default
    // findAll(), findById(), save(), delete(), etc.
}