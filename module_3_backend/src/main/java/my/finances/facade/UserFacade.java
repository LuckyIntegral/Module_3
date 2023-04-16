package my.finances.facade;

import my.finances.dto.UserCreatedDTO;
import my.finances.dto.UserDetails;
import my.finances.dto.UserWithAccountNumberDTO;
import my.finances.persistence.entity.User;

import java.util.Collection;

public interface UserFacade extends MutableEntityFacade<User> {
    void create(UserCreatedDTO entity);
    UserDetails findById(long id);
    Collection<User> findAll();
    Collection<UserWithAccountNumberDTO> findAllUsersWithNumberOfAccount();
}
