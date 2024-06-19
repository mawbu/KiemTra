package com._7.QLNhanSu.services;

import com._7.QLNhanSu.entity.PhongBan;
import com._7.QLNhanSu.repository.NhanVienRepository;
import com._7.QLNhanSu.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongbanRepository;

    @Autowired
    public PhongBanService(PhongBanRepository phongbanRepository) {
        this.phongbanRepository = phongbanRepository;
    }

    public List<PhongBan> getAllPhongban() {
        return phongbanRepository.findAll();
    }

    public Optional<PhongBan> getPhongbanById(Long id) {
        return phongbanRepository.findById(id);
    }

    public PhongBan addPhongban(PhongBan phongban) {
        return phongbanRepository.save(phongban);
    }

    public PhongBan updatePhongban(PhongBan phongban) {
        PhongBan existingPhongban = phongbanRepository.findById(phongban.getId())
                .orElseThrow(() -> new IllegalStateException("Department with MaPhong " +
                        phongban.getMaPhong() + " does not exist."));
        existingPhongban.setTenPhong(phongban.getTenPhong());
        existingPhongban.setNhanViens(phongban.getNhanViens());
        return phongbanRepository.save(existingPhongban);
    }

    public void deletePhongbanById(Long id) {
        phongbanRepository.deleteById(id);
    }
}
