package my.finances.facade;

import my.finances.dto.AccountShortInfo;
import my.finances.dto.AccountWithTransactionsDTO;
import my.finances.persistence.entity.Account;

import java.util.Collection;

public interface AccountFacade extends MutableEntityFacade<Account> {
    void create(Account entity, Long ownerId);
    AccountWithTransactionsDTO findById(long id);
    Collection<AccountShortInfo> findAll();
}
