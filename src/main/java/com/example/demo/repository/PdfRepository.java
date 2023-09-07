package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modal.*;

public interface PdfRepository extends JpaRepository<PdfData, Long> {
}
