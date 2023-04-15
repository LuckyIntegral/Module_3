package my.finances.api;

import my.finances.model.TransactionDetailsModel;
import my.finances.model.TransactionModel;

import java.util.Collection;
import java.util.Optional;

public interface TransactionApiService {
    Optional<TransactionDetailsModel> findById(Long id);
    Collection<TransactionModel> findAll();
}
