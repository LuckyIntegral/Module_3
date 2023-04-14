package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.dto.TransactionDetails;
import my.finances.dto.TransactionShortInfo;
import my.finances.facade.TransactionFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionFacade transactionFacade;

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetails> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<TransactionShortInfo>> findAll() {
        return ResponseEntity.ok(transactionFacade.findAll());
    }
}
