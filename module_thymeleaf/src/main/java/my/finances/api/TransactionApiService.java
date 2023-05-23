package my.finances.api;

import my.finances.model.TransactionDetailsModel;
import my.finances.model.TransactionModel;
import my.finances.model.TransactionPostModel;

import java.util.Collection;
import java.util.Optional;

public interface TransactionApiService {
    Boolean create(TransactionPostModel transactionPostModel);
    Optional<TransactionDetailsModel> findById(Long id);
    Collection<TransactionModel> findAll();
}
