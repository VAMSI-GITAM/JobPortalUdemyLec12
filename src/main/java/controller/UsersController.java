package controller;


import entity.Users;
import entity.UsersType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import services.UsersService;
import services.UsersTypeService;

import java.util.List;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService){
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersType = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersType);
        model.addAttribute("user", new Users());
        return "register";  // This should match the file name in the templates folder
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users){
        System.out.println("User:: "+users);
        usersService.addNew(users);
        return "dashboard";
    }
}
