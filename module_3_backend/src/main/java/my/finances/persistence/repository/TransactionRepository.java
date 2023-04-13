package my.finances.persistence.repository;

import my.finances.persistence.entity.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends BaseEntityRepository<Transaction> {
}
