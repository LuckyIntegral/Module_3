package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import my.finances.persistence.entity.Transaction;
import my.finances.persistence.repository.TransactionRepository;
import my.finances.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public void create(Transaction entity) {
        transactionRepository.save(entity);
    }

    @Override
    public Transaction findById(Long id) {
        if (transactionRepository.findById(id).isPresent()) {
            return transactionRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
