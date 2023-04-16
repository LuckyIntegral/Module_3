package my.finances.facade;

import my.finances.dto.TransactionCreatedDTO;
import my.finances.dto.TransactionDetails;
import my.finances.dto.TransactionShortInfo;
import my.finances.persistence.entity.Transaction;

import java.util.Collection;

public interface TransactionFacade {
    void create(TransactionCreatedDTO transactionDTO);
    Collection<TransactionShortInfo> findAll();
    TransactionDetails findById(long id);
}
