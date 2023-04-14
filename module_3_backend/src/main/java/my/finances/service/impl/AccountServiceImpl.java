package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.Account;
import my.finances.persistence.repository.AccountRepository;
import my.finances.persistence.repository.TransactionRepository;
import my.finances.persistence.repository.UserRepository;
import my.finances.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Collection<Account> findByUserId(long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
        Collection<Account> accounts = accountRepository.findAllByOwnerId(id);
        return Objects.requireNonNullElse(accounts, Collections.emptyList());
    }

    @Override
    @Transactional
    public void create(Account entity, Long ownerId) {
        checkAccountData(entity);
        if (userRepository.findById(ownerId).isEmpty()) {
            throw new EntityNotFoundException("Owner doesn't exist");
        } else {
            entity.setOwner(userRepository.findById(ownerId).get());
            accountRepository.save(entity);
        }
    }

    @Override
    @Transactional
    public Account findById(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            return accountRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
    }

    @Transactional
    @Override
    public Collection<Account> findAll() {
        return accountRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Account entity, Long id) {
        checkAccountData(entity);
        if (accountRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Entity doesn't exist");
        } else {
            entity.setId(id);
            entity.setOwner(accountRepository.findById(id).get().getOwner());
            accountRepository.save(entity);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (accountRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Owner doesn't exist");
        } else {
            transactionRepository.findAllByAccountId(id).forEach(e -> transactionRepository.deleteById(e.getId()));
            accountRepository.deleteById(id);
        }
    }

    private void checkAccountData(Account entity) {
        if (entity.getBalance() < 0) {
            throw new InvalidDataException("Invalid balance number");
        }
        if (entity.getName() == null || entity.getName().equals("")) {
            throw new InvalidDataException("Invalid data");
        }
    }
}
