package ru.gb.lesson7.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.lesson7.Entities.User;
import ru.gb.lesson7.Services.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage() { return "home"; }

    @GetMapping("/unsecured")
    public String unsecuredPage() { return "unsecured"; }

    @GetMapping("/auth_page")
    public String authPage() { return "auth_page"; }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ADMIN')")
    public String adminPage() { return "admin"; }

    @GetMapping("/user-info")
    public String daoUserInfo(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(()-> new RuntimeException(String.format("User '%s' not found.", principal.getName())));
        return "Auth user - " + user.getUsername();
    }
}
