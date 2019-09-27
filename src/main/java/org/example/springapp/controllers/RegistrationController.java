package org.example.springapp.controllers;


import org.example.springapp.entitys.Role;
import org.example.springapp.entitys.User;
import org.example.springapp.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }




    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());  // Проверяем есть ли user с таким именем в БД

        if (userFromDb != null) {
            model.addAttribute("message", "User EXIST");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }


}
