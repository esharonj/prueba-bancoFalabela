package com.prueba.bancofalabella.prueba.tecnica.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "phones")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String number;


    @Column(nullable = false)
    private String citycode;


    @Column(nullable = false)
    private String contrycode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
