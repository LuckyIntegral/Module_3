package my.finances.facade.Impl;

import lombok.AllArgsConstructor;
import my.finances.dto.AccountShortInfo;
import my.finances.dto.UserCreatedDTO;
import my.finances.dto.UserDetails;
import my.finances.dto.UserWithAccountNumberDTO;
import my.finances.facade.UserFacade;
import my.finances.persistence.entity.User;
import my.finances.service.AccountService;
import my.finances.service.TransactionService;
import my.finances.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    @Override
    public void create(UserCreatedDTO entity) {
        userService.create(entity);
    }

    @Override
    public UserDetails findById(long id) {
        return new UserDetails(
                userService.findById(id),
                accountService.findByUserId(id)
                        .stream()
                        .map(e -> new AccountShortInfo(e, transactionService.findAllByAccountId(e.getId()).size()))
                        .toList()
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
