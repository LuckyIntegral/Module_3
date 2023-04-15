package my.finances.api.impl;

import my.finances.api.TransactionApiService;
import my.finances.model.TransactionDetailsModel;
import my.finances.model.TransactionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionApiServiceImpl implements TransactionApiService {
    @Value("${finance.backend.api.url}")
    private String apiUrl;

    @Override
    public Optional<TransactionDetailsModel> findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionDetailsModel> responseEntity = restTemplate.exchange(
                apiUrl + "/transactions/" + id,
                HttpMethod.GET,
                null,
                TransactionDetailsModel.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TransactionDetailsModel transactionDetailsModel = responseEntity.getBody();
            if (transactionDetailsModel != null) {
                return Optional.of(transactionDetailsModel);
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<TransactionModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/transactions",
                HttpMethod.GET,
                null,
                TransactionModel[].class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TransactionModel[] transactionModels = responseEntity.getBody();
            if (transactionModels != null) {
                return List.of(transactionModels);
            }
        }

        return Collections.emptyList();
    }
}
