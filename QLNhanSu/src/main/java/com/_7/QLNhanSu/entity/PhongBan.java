package com._7.QLNhanSu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "PhongBan")
@Data
public class PhongBan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "MaPhong")
    private String MaPhong;

    @NotNull
    @Column(name = "TenPhong")
    private String TenPhong;

    @OneToMany(mappedBy = "phongBan")
    private List<NhanVien> nhanViens;
}

