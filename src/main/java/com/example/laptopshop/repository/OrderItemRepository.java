package com.example.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {

}
