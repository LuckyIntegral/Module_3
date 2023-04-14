package my.finances.service;

import my.finances.persistence.entity.Transaction;

import java.util.Collection;

public interface TransactionService extends BaseEntityService<Transaction> {
    void create(Transaction entity, Long accId);
    Collection<Transaction> findAllByAccountId(long id);
}
