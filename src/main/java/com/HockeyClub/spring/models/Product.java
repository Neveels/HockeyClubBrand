package com.HockeyClub.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Product")
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
//    @Lob
//    @Type(type="org.hibernate.type.BinaryType")
//    private byte[] bytes;

    @PrePersist
    private void init(){
        dateOfAdded = LocalDateTime.now();
    }

}
