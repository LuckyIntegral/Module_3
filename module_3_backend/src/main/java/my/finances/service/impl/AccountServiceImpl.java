package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import my.finances.persistence.entity.Account;
import my.finances.persistence.repository.AccountRepository;
import my.finances.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public void create(Account entity) {
        accountRepository.save(entity);
    }

    @Override
    public Account findById(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
    }

    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void update(Account entity) {
        accountRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
}
