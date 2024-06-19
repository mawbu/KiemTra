package com._7.QLNhanSu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home/index"; // Tên của view sẽ được trả về, ví dụ: home.html
    }
}
