package com.example.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.laptopshop.entity.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    
    List<Orders> findByUser_Id(Integer userId);

    @Query("SELECT o FROM Orders o JOIN FETCH o.user")
    List<Orders> findAllWithUser(); // ✅ thêm dòng này
}
