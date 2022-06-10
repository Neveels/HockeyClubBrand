package com.ClubProduction.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    private LocalDateTime dateOfAdded;
    private Integer quantity;
    private Integer price;
    private String url;

    @PrePersist
    private void init() {
        dateOfAdded = LocalDateTime.now();
    }

}
