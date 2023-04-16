package my.finances.api;

import my.finances.model.AccountDetailsModel;
import my.finances.model.AccountModel;

import java.util.Collection;
import java.util.Optional;

public interface AccountApiService {
    Optional<AccountDetailsModel> findById(Long id);
    Collection<AccountModel> findAll();
}
