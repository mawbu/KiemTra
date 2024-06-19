package com._7.QLNhanSu.repository;

import com._7.QLNhanSu.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
    List<NhanVien> findByTenNVContainingIgnoreCase(String tenNV);
}
