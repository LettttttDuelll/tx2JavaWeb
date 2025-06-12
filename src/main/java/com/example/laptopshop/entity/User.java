package com.example.laptopshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    // Thông tin hồ sơ
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "job_area")
    private String jobArea;
    @Column(name = "job")
    private String job;
    @Column(name = "position")
    private String position;
    @Column(name = "apply_year")
    private Integer applyYear;
    @Column(name = "user_notes")
    private String userNotes;
    @Column(name = "roles")
    private String roles;
    @Column(name = "logined")
    private boolean logined;
    @Column(name = "user_actions")
    private String userActions;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
    @Column(name = "last_logined")
    private LocalDateTime lastLogined;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "deleted")
    private Boolean deleted;
}
