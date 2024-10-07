package com.erp.techInovate.techInovate.controller;

import com.erp.techInovate.techInovate.entity.EmployeeEntity;
import com.erp.techInovate.techInovate.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class LoginController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // login.html로 이동
    }

    @PostMapping("/login")
    public String login(@RequestParam String employeeNumber, @RequestParam String email, Model model, HttpSession session) {
        Optional<EmployeeEntity> employee = employeeService.login(employeeNumber, email);

        if (employee.isPresent()) {
            // 로그인 성공 시 세션에 employeeId 저장
            session.setAttribute("employeeId", employee.get().getEmployeeId()); // employeeId를 세션에 저장

            model.addAttribute("message", "로그인 성공!");
            return "redirect:/hrm"; // 홈 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "로그인 실패! 사원번호와 이메일을 확인하세요.");
            return "login"; // 다시 로그인 페이지로 이동
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그인 페이지로 리다이렉트
    }
}
