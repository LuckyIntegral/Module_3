package my.finances.persistence.repository;

import my.finances.persistence.entity.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends BaseEntityRepository<Account> {
}
