package my.finances.service;

import my.finances.dto.UserCreatedDTO;
import my.finances.persistence.entity.User;

public interface UserService extends MutableEntityService<User> {
    void create(UserCreatedDTO entity);
}
