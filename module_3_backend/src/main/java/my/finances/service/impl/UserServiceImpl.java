package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import my.finances.persistence.entity.User;
import my.finances.persistence.repository.UserRepository;
import my.finances.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
