package my.finances.facade.Impl;

import lombok.AllArgsConstructor;
import my.finances.dto.UserWithAccountNumberDTO;
import my.finances.facade.UserFacade;
import my.finances.persistence.entity.User;
import my.finances.service.AccountService;
import my.finances.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final AccountService accountService;

    @Override
    public void create(User entity) {
        userService.create(entity);
    }

    @Override
    public UserWithAccountNumberDTO findById(long id) {
        return new UserWithAccountNumberDTO(
                userService.findById(id),
                accountService.findByUserId(id).size()
        );
    }

    @Override
    public void update(User entity, long id) {
        userService.update(entity, id);
    }

    @Override
    public void delete(long id) {
        userService.delete(id);
    }

    @Override
    public Collection<User> findAll() {
        return userService.findAll();
    }

    @Override
    public Collection<UserWithAccountNumberDTO> findAllUsersWithNumberOfAccount() {
        return userService.findAll()
                .stream()
                .map(e -> new UserWithAccountNumberDTO(e, accountService.findByUserId(e.getId()).size()))
                .toList();
    }
}
