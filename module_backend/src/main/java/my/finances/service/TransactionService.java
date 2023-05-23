package my.finances.service;

import my.finances.dto.TransactionCreatedDTO;
import my.finances.persistence.entity.Transaction;

import java.util.Collection;

public interface TransactionService extends BaseEntityService<Transaction> {
    void create(TransactionCreatedDTO transactionDTO);
    Collection<Transaction> findAllByAccountId(long id);
}
