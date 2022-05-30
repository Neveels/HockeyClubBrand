package com.ClubProduction.spring.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String country;

    private Integer age;

    private Integer goal;

    private Integer assist;

    private String image;

    private Integer numberOfPlayer;

    private String name;

    @Enumerated(EnumType.STRING)
    private PlayerRole role;

}
