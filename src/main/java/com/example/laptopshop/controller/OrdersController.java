package com.example.laptopshop.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.laptopshop.entity.Orders;
import com.example.laptopshop.entity.OrdersPDFExport;
import com.example.laptopshop.entity.SingleOrderExport;
import com.example.laptopshop.repository.OrdersRepository;
import com.example.laptopshop.service.OrdersServiceImpl;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class OrdersController {
    @Autowired
    private OrdersServiceImpl ordersService;
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/orders/export")
    public void exportToPdf(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentTime= dateFormat.format(new Date());

        String HeaderKey = "Content-Disposition";
        String HeaderValue = "attachment; filename = orders"+currentTime +".pdf";
        response.setHeader(HeaderKey,HeaderValue);

        List<Orders> listOrders= ordersService.getAllOrders();
        OrdersPDFExport exporter = new OrdersPDFExport(listOrders);
        exporter.export(response);
    }

    @GetMapping("/orders/export/{id}") // single with @PathVariable
    public void exportSingleToPdf( @PathVariable Integer id, HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime= dateFormat.format(new Date());

        String HeaderKey = "Content-Disposition";
        String HeaderValue = "attachment; filename = orders #"+id+" "+currentTime +".pdf";
        response.setHeader(HeaderKey,HeaderValue);

        Orders order = ordersService.getOrderById(id).orElseThrow();
        SingleOrderExport exporter = new SingleOrderExport(order);
        exporter.export(response);
    }

    @PostMapping("/orders/update-status")
    public String updateOrderStatus(@RequestParam("orderId") Integer orderId,
                                    @RequestParam("status") String status) {
        Orders order = ordersRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            ordersRepository.save(order);
        }
        return "redirect:/admin/orderList";
    }

}
