package my.finances.persistence.repository;

import jakarta.websocket.server.PathParam;
import my.finances.persistence.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AccountRepository extends BaseEntityRepository<Account> {
    @Query("from Account where owner.id=:id")
    Collection<Account> findAllByOwnerId(@PathParam("id") long id);
}
