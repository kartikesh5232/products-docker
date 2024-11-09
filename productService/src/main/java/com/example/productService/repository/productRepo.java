package com.example.productService.repository;

import com.example.productService.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface productRepo extends JpaRepository<product, Long> {
    Optional<product> findByName(String name);


}