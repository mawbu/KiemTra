package com._7.QLNhanSu.controller;

import com._7.QLNhanSu.entity.NhanVien;
import com._7.QLNhanSu.services.NhanVienService;
import com._7.QLNhanSu.services.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class    NhanVienController {

    @Autowired
    private NhanVienService nhanvienService;

    @Autowired
    private PhongBanService phongbanService;

    @GetMapping
    public String showNhanvienList(Model model, @RequestParam(name = "page", defaultValue = "1") int page) {
        Page<NhanVien> nhanvienPage = nhanvienService.getNhanviensPaginated(page - 1, 5);
        List<NhanVien> nhanviens = nhanvienPage.getContent();

        model.addAttribute("nhanviens", nhanviens);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nhanvienPage.getTotalPages());

        return "nhanviens/nhanvien-list";
    }

    @GetMapping("/add")
    public String showAddNhanvienForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongbans", phongbanService.getAllPhongban());
        return "nhanviens/add-nhanvien";
    }

    @PostMapping("/add")
    public String addNhanvien(@ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", nhanvien);
            model.addAttribute("phongbans", phongbanService.getAllPhongban());
            return "nhanviens/add-nhanvien";
        }

        nhanvienService.addNhanvien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/edit/{id}")
    public String showEditNhanvienForm(@PathVariable("id") Long id, Model model) {
        NhanVien nhanvien = nhanvienService.getNhanvienById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid nhân viên id: " + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("phongbans", phongbanService.getAllPhongban());
        return "nhanviens/update-nhanvien";
    }

    @PostMapping("/update/{id}")
    public String updateNhanvien(@PathVariable("id") Long id, @Valid @ModelAttribute("nhanvien") NhanVien nhanvien, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("nhanvien", nhanvien);
            model.addAttribute("phongbans", phongbanService.getAllPhongban());
            return "nhanviens/update-nhanvien";
        }

        nhanvien.setId(id);
        nhanvienService.updateNhanvien(nhanvien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/delete/{id}")
    public String deleteNhanvien(@PathVariable("id") Long id) {
        nhanvienService.deleteNhanvienById(id);
        return "redirect:/nhanviens";
    }
}
