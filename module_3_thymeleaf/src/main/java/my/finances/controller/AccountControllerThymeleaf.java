package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.api.AccountApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountControllerThymeleaf {

    private final AccountApiService accountApiService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("accounts", accountApiService.findAll());
        return "elp/accounts";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        if (accountApiService.findById(id).isPresent()) {
            model.addAttribute("account", accountApiService.findById(id).get());
            return "edp/account_details";
        }
        return "404";
    }
}