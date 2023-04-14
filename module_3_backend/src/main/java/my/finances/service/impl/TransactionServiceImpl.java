package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.Transaction;
import my.finances.persistence.repository.AccountRepository;
import my.finances.persistence.repository.TransactionRepository;
import my.finances.persistence.types.TransactionType;
import my.finances.service.AccountService;
import my.finances.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    @Override
    public void create(Transaction entity, Long accId) {
        if (accountRepository.findById(accId).isEmpty()) {
            throw new EntityNotFoundException("Entity does not exist");
        }
        entity.setAccount(accountRepository.findById(accId).get());
        if (entity.getTransactionType() == null) {
            throw new InvalidDataException("Invalid type");
        }
        if (entity.getAmount() <= 0) {
            throw new InvalidDataException("Invalid suma");
        }
        if (entity.getTransactionType().equals(TransactionType.EXPENSE)) {
            if (entity.getAccount().getBalance() < entity.getAmount()) {
                throw new InvalidDataException("Insufficient funds");
            }
            entity.getAccount().setBalance(entity.getAccount().getBalance() - entity.getAmount());
        } else {
            entity.getAccount().setBalance(entity.getAccount().getBalance() + entity.getAmount());
        }
        accountService.update(entity.getAccount(), entity.getAccount().getId());
        transactionRepository.save(entity);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exist"));
    }

    @Override
    public Collection<Transaction> findAllByAccountId(long id) {
        return transactionRepository.findAllByAccountId(id);
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
