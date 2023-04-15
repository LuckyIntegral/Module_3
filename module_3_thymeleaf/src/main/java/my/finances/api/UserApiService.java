package my.finances.api;

import my.finances.model.UserDetailsModel;
import my.finances.model.UserWithAccNumberModel;

import java.util.Collection;
import java.util.Optional;

public interface UserApiService {
    Optional<UserDetailsModel> findById(Long id);
    Collection<UserWithAccNumberModel> findAll();
}
