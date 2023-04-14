package my.finances.persistence.repository;

import jakarta.websocket.server.PathParam;
import my.finances.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransactionRepository extends BaseEntityRepository<Transaction> {
    @Query("from Transaction where account.id=:id")
    Collection<Transaction> findAllByAccountId(@PathParam("id") long id);

    @Query("delete from Transaction t where t.account.id=:id")
    void deleteAllByAccountId(@PathParam("id") long id);
}
