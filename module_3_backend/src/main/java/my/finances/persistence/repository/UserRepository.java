package my.finances.persistence.repository;

import my.finances.persistence.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseEntityRepository<User> {
}
