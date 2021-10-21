package com.example.mylittlebank;


import com.example.mylittlebank.model.User;
import com.example.mylittlebank.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private UserRepo userRepo;


    @GetMapping("/main")
    public String main(Model model){

        Iterable<User> users=userRepo.findAll();

        model.addAttribute("users", users);

        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                      @RequestParam String address, @RequestParam String date, Model model){

        User user=new User(name, email, phone, address, LocalDate.parse(date));
        userRepo.save(user);
        Iterable<User> users=userRepo.findAll();

        model.addAttribute("users", users);
        return "main";
    }

    @GetMapping()
    public String greeting(){
        return "home";
    }

}
