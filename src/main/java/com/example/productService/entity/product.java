package com.example.productService.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price", length = 1000)
    private int price;

    @Column(name = "category", length = 100)
    private String category;

    @Lob
    @Column(name = "imageData", length = 100000)
    private byte[] imageData;
}

