package my.finances.facade;

import my.finances.dto.TransactionDetails;
import my.finances.dto.TransactionShortInfo;
import my.finances.persistence.entity.Transaction;

import java.util.Collection;

public interface TransactionFacade {
    void create(Transaction transaction, Long accId);
    Collection<TransactionShortInfo> findAll();
    TransactionDetails findById(long id);
}
