package my.finances.controller;

import lombok.AllArgsConstructor;
import my.finances.dto.UserCreatedDTO;
import my.finances.dto.UserDetails;
import my.finances.dto.UserWithAccountNumberDTO;
import my.finances.facade.AccountFacade;
import my.finances.facade.UserFacade;
import my.finances.persistence.entity.Account;
import my.finances.persistence.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;
    private final AccountFacade accountFacade;

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody UserCreatedDTO entity) {
        userFacade.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> createAccount(@RequestBody Account entity, @PathVariable Long id) {
        accountFacade.create(entity, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody User entity, @PathVariable Long id) {
        userFacade.update(entity, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<Collection<UserWithAccountNumberDTO>> findAllUsersWithNumberOfAccount() {
        return ResponseEntity.ok(userFacade.findAllUsersWithNumberOfAccount());
    }
}
