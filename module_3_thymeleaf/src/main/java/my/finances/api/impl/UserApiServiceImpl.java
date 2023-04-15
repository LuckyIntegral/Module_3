package my.finances.api.impl;

import my.finances.api.UserApiService;
import my.finances.model.UserDetailsModel;
import my.finances.model.UserModel;
import my.finances.model.UserWithAccNumberModel;
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
public class UserApiServiceImpl implements UserApiService {
    @Value("${finance.backend.api.url}")
    private String apiUrl;

    @Override
    public Optional<UserDetailsModel> findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDetailsModel> responseEntity = restTemplate.exchange(
                apiUrl + "/users/" + id,
                HttpMethod.GET,
                null,
                UserDetailsModel.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            UserDetailsModel userModel = responseEntity.getBody();
            if (userModel != null) {
                return Optional.of(userModel);
            }
        }
        return Optional.empty();
    }

    @Override
    public Collection<UserWithAccNumberModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserWithAccNumberModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/users",
                HttpMethod.GET,
                null,
                UserWithAccNumberModel[].class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            UserWithAccNumberModel[] userModels = responseEntity.getBody();
            if (userModels != null) {
                return List.of(userModels);
            }
        }

        return Collections.emptyList();
    }
}
