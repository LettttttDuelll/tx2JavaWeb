package com.example.laptopshop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.laptopshop.entity.OrderItem;
import com.example.laptopshop.entity.Orders;
import com.example.laptopshop.repository.OrdersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    @Override
    public Orders placOrders(int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placOrders'");
    }

    private Double caculateTotalPrice(List<OrderItem> orders){
        double tong = 0;
        for (OrderItem Item : orders) {
           tong+= Item.getPrice()*Item.getQuantity();
        }
        return tong;
    }




    @Override
    public Orders getOrders(int userId) {
        return ordersRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("Không tìm thấy đơn hàng"));
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> list = ordersRepository.findAll();
        System.out.println("Số đơn hàng: " + list.size());
        return list;
    }

    public Optional<Orders> getOrderById(Integer id) {
        return ordersRepository.findById(id);
    }
    
}
