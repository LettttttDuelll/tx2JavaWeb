package com.example.laptopshop.dto;

import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ProfileForm {
    private String username;
    private String password;
    // Thông tin hồ sơ
    private String fullName;
    private LocalDate birthday;
    private String phone;
    private String email;
    private String address;

    private String jobArea;
    private String job;
    private String position;
    private Integer applyYear;

    private String userNotes;
    private String roles;
    private boolean logined;

    private String userActions;

    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime lastLogined;
}
