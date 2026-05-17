package com.example.demo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Search by name containing keyword
    List<Student> findByNameContainingIgnoreCase(String name);

    // Pagination + search
    Page<Student> findByNameContainingIgnoreCase(String name, Pageable pageable);
}