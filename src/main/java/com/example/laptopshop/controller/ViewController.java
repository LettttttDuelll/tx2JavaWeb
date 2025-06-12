package com.example.laptopshop.controller;

import com.example.laptopshop.dto.LoginRequest;
import com.example.laptopshop.dto.RegisterRequest;
import com.example.laptopshop.entity.Laptop;
import com.example.laptopshop.entity.Orders;
import com.example.laptopshop.service.LaptopServiceImpl;
import com.example.laptopshop.service.OrdersServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.laptopshop.service.UserService;
import com.example.laptopshop.entity.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {
    @Autowired
    private UserService userService;

    @Autowired
    private LaptopServiceImpl laptopService;

    @Autowired
    private OrdersServiceImpl ordersService;

    @GetMapping("/custom-login")
    public String Login(Model model) {
        model.addAttribute("loginRequest" , new LoginRequest());
        return "login/login";
    }
    @GetMapping("/dashboard")
    public String Dashboard(Model model, Principal principal) {
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUserName(username);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "dashboard/dashboard";
    }
    @GetMapping("/register")
    public String Register(Model model) {
        model.addAttribute("registerRequest" , new RegisterRequest());
        return "login/register";
    }
    @GetMapping("/user_list")
    public String listUsers(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        Collections.reverse(users);
        model.addAttribute("users", users);
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUserName(username);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        //model.addAttribute(username, userOptional);
        return "dashboard/list_user";
    }
    @GetMapping("/bin")
    public String Bin(Model model, Principal principal){
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUserName(username);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "dashboard/bin";
    }
    @GetMapping("/cart")
    public String Cart(){
        return "end_user/cart";
    }
    @GetMapping("/home")
    public String Home(Model model) {
        List<Laptop> laptops = laptopService.getAllLaptops();
        model.addAttribute("products", laptops);
        return "end_user/home";
    }
    @GetMapping("/detailproduct")
    public String Detail_product(@RequestParam("id") Long productId, Model model) {
        Optional<Laptop> laptops = laptopService.getLaptopById(productId);
        laptops.ifPresent(laptop -> model.addAttribute("laptop", laptop));
        return "end_user/detailproduct";
    }
    @GetMapping("/order")
    public String Order(Model model) {
        return "end_user/order";
    }
    @GetMapping("/productList")
    public String ProductList(Model model, Principal principal) {
        List<Laptop> laptops = laptopService.getAllLaptops();
        Collections.reverse(laptops);
        String username = principal.getName();
        Optional<User> userOptional = userService.findByUserName(username);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        model.addAttribute("laptops", laptops);
        return "dashboard/DsType";
    }
    @GetMapping("/admin/orderList")
    public String OrderList(Model model){
        List<Orders > a = ordersService.getAllOrders();
        Collections.reverse(a);
        model.addAttribute("list", a);
        return "dashboard/list_order";
    }
}
