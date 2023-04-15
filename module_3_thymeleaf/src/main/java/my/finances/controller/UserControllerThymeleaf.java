package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.api.UserApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserControllerThymeleaf {

    private final UserApiService userApiService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userApiService.findAll());
        return "elp/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        if (userApiService.findById(id).isPresent()) {
            model.addAttribute("user_det", userApiService.findById(id).get());
            return "edp/user_details";
        }
        return "404";
    }
}
