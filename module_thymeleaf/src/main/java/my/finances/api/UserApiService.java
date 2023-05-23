package my.finances.api;

import my.finances.model.AccountPostModel;
import my.finances.model.UserDetailsModel;
import my.finances.model.UserModel;
import my.finances.model.UserWithAccNumberModel;

import java.util.Collection;
import java.util.Optional;

public interface UserApiService {
    Boolean createAccount(AccountPostModel account, Long id);
    Boolean create(UserModel user);
    Optional<UserDetailsModel> findById(Long id);
    Collection<UserWithAccNumberModel> findAll();
}
