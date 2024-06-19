package com._7.QLNhanSu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "NhanVien")
@Data
public class NhanVien {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "Ma_NV", length = 3, nullable = false)
    private Long maNV;

    @Column(name = "Ten_NV", length = 100, nullable = false)
    private String tenNV;

    @Column(name = "Phai", length = 3)
    private String phai;

    @Column(name = "Noi_Sinh", length = 200)
    private String noiSinh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Ma_Phong", nullable = false)
    private PhongBan phongBan;

    @Column(name = "Luong")
    private int luong;

}

