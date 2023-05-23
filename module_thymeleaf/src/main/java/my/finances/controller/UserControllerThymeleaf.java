package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.api.UserApiService;
import my.finances.model.AccountPostModel;
import my.finances.model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserControllerThymeleaf {

    private final UserApiService userApiService;

    @GetMapping("/new")
    public String createUserMenu(Model model) {
        model.addAttribute("user", new UserModel());
        return "nep/user_new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute UserModel user) {
        if (!userApiService.create(user)) {
            return "400";
        }
        return "redirect:/users";
    }

    @GetMapping("/{id}/new")
    public String createAccountMenu(@PathVariable Long id, Model model) {
        model.addAttribute("account", new AccountPostModel());
        model.addAttribute("owner_id", id);
        return "nep/account_new";
    }

    @PostMapping("/{id}/new")
    public String createAccount(@PathVariable Long id, @ModelAttribute AccountPostModel account) {
        if (!userApiService.createAccount(account, id)) {
            return "400";
        }
        return "redirect:/users/" + id;
    }

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
