package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="giao_dich")
public class TransactionInfo {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String code;
   private LocalDate date;
   private Double price;
   private Double square;

   @ManyToOne
   @JoinColumn(name="customer_id")
   private Customer customer;

   @ManyToOne
   @JoinColumn(name="category_id")
   private Category category;

}
