package my.finances.facade.Impl;

import lombok.AllArgsConstructor;
import my.finances.dto.AccountShortInfo;
import my.finances.dto.AccountWithTransactionsDTO;
import my.finances.dto.TransactionShortInfo;
import my.finances.facade.AccountFacade;
import my.finances.persistence.entity.Account;
import my.finances.service.AccountService;
import my.finances.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public Collection<AccountShortInfo> findAll() {
        return accountService.findAll()
                .stream()
                .map(e -> new AccountShortInfo(e, transactionService.findAllByAccountId(e.getId()).size()))
                .toList();
    }

    @Override
    public void create(Account entity, Long ownerId) {
        accountService.create(entity, ownerId);
    }

    @Override
    public AccountWithTransactionsDTO findById(long id) {
        return new AccountWithTransactionsDTO(
                accountService.findById(id),
                transactionService.findAllByAccountId(id)
                        .stream()
                        .map(TransactionShortInfo::new)
                        .toList()
        );
    }

    @Override
    public void update(Account entity, long id) {
        accountService.update(entity, id);
    }

    @Override
    public void delete(long id) {
        accountService.delete(id);
    }
}
