package org.example.controller;

import org.example.service.LoginService;
import org.example.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class LoginController {
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping({"/", "/index"})
    public String home() {
        return "index";
    }
    @PostMapping("/login")
    public String login(@Validated User user, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()){
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(fieldError);
                model.addAttribute(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return "index";
        }
        Object o = loginService.login(user);

        if (o != null) {
            String[] types = {"teacher", "textbookAdmin", "buyer", "warehouseKeeper"};
            session.setAttribute(types[user.getUserType()], o);
            return "redirect:"+types[user.getUserType()]+"/main";

        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "index";
        }
    }
}
