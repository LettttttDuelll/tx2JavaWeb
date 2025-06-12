package com.example.laptopshop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_item")
@Data
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "laptop_id")
    private Laptop laptop;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

}


