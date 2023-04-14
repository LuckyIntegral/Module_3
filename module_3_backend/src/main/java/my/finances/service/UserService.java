package my.finances.service;

import my.finances.persistence.entity.User;

public interface UserService extends MutableEntityService<User> {
    void create(User entity);
}
