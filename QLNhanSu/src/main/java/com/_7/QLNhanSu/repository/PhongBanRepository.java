package com._7.QLNhanSu.repository;

import com._7.QLNhanSu.entity.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {

}

