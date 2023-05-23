package my.finances.service;

import my.finances.persistence.entity.Account;

import java.util.Collection;

public interface AccountService extends MutableEntityService<Account> {
    void create(Account entity, Long ownerId);
    Collection<Account> findByUserId(long id);
}
