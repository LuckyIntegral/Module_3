package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.api.TransactionApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionControllerThymeleaf {
    private final TransactionApiService transactionApiService;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("transactions", transactionApiService.findAll());
        return "elp/transactions";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        if (transactionApiService.findById(id).isPresent()) {
            model.addAttribute("transaction", transactionApiService.findById(id).get());
            return "edp/transaction_details";
        }
        return "404";
    }
}
