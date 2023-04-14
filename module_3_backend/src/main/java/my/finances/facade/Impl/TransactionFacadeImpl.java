package my.finances.facade.Impl;

import lombok.AllArgsConstructor;
import my.finances.dto.TransactionDetails;
import my.finances.dto.TransactionShortInfo;
import my.finances.facade.TransactionFacade;
import my.finances.persistence.entity.Transaction;
import my.finances.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;

    @Override
    public void create(Transaction entity, Long accId) {
        transactionService.create(entity, accId);
    }

    @Override
    public TransactionDetails findById(long id) {
        return new TransactionDetails(transactionService.findById(id));
    }

    @Override
    public Collection<TransactionShortInfo> findAll() {
        return transactionService.findAll()
                .stream()
                .map(TransactionShortInfo::new)
                .toList();
    }
}
