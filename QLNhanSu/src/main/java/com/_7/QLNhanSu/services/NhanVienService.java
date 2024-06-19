package com._7.QLNhanSu.services;

import com._7.QLNhanSu.entity.NhanVien;
import com._7.QLNhanSu.entity.PhongBan;
import com._7.QLNhanSu.repository.NhanVienRepository;
import com._7.QLNhanSu.repository.PhongBanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    private final NhanVienRepository nhanvienRepository;
    private final PhongBanRepository phongbanRepository;

    @Autowired
    public NhanVienService(NhanVienRepository nhanvienRepository, PhongBanRepository phongbanRepository) {
        this.nhanvienRepository = nhanvienRepository;
        this.phongbanRepository = phongbanRepository;
    }

    public List<NhanVien> getAllNhanvien() {
        return nhanvienRepository.findAll();
    }

    public Optional<NhanVien> getNhanvienById(Long id) {
        return nhanvienRepository.findById(id);
    }

    public NhanVien addNhanvien(NhanVien nhanvien) {
        Long phongbanId = nhanvien.getPhongBan().getId();
        PhongBan phongban = phongbanRepository.findById(phongbanId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Phongban ID"));

        nhanvien.setPhongBan(phongban);

        return nhanvienRepository.save(nhanvien);
    }

    public NhanVien updateNhanvien(NhanVien nhanvien) {
        Optional<NhanVien> optionalNhanvien = nhanvienRepository.findById(nhanvien.getId());
        if (optionalNhanvien.isEmpty()) {
            throw new IllegalStateException("Nhanvien with id " + nhanvien.getId() + " does not exist.");
        }

        return nhanvienRepository.save(nhanvien);
    }

    public void deleteNhanvienById(Long id) {
        if (!nhanvienRepository.existsById(id)) {
            throw new IllegalStateException("Nhanvien with id " + id + " does not exist.");
        }
        nhanvienRepository.deleteById(id);
    }

    public Page<NhanVien> getNhanviensPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return nhanvienRepository.findAll(pageable);
    }
}
