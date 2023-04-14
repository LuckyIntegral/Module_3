package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.User;
import my.finances.persistence.repository.UserRepository;
import my.finances.service.AccountService;
import my.finances.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    @Override
    public void create(User entity) {
        if (entity.getFirstName() == null || entity.getFirstName().equals("")) {
            throw new InvalidDataException("Invalid name");
        }
        if (entity.getLastName() == null || entity.getLastName().equals("")) {
            throw new InvalidDataException("Invalid lastname");
        }
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
    public void update(User entity, Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
        if (entity.getFirstName() == null || entity.getFirstName().equals("")) {
            throw new InvalidDataException("Invalid name");
        }
        if (entity.getLastName() == null || entity.getLastName().equals("")) {
            throw new InvalidDataException("Invalid lastname");
        }
        entity.setId(id);
        userRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
        accountService.findByUserId(id)
                .forEach(e -> accountService.delete(e.getId()));
        userRepository.deleteById(id);
    }
}
