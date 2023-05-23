package my.finances.service;

import jakarta.persistence.EntityNotFoundException;
import my.finances.dto.UserCreatedDTO;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.User;
import my.finances.persistence.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCreateValidUserData() {
        UserCreatedDTO createdDTO = new UserCreatedDTO();
        createdDTO.setFirstName("Jon");
        createdDTO.setLastName("Jonson");

        int before = userService.findAll().size();
        userService.create(createdDTO);
        int after = userService.findAll().size();

        Assertions.assertEquals(before + 1, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidUserData() {
        int before = userService.findAll().size();

        UserCreatedDTO createdDTO = new UserCreatedDTO();
        createdDTO.setFirstName("");
        createdDTO.setLastName("");

        Assertions.assertThrows(InvalidDataException.class, () -> userService.create(createdDTO));

        int after = userService.findAll().size();
        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testFindAll() {
        int before = userService.findAll().size();

        User user1 = randomUser();
        userRepository.save(user1);

        User user2 = randomUser();
        userRepository.save(user2);

        int after = userService.findAll().size();

        Assertions.assertEquals(before + 2, after);
    }

    @Test
    @Transactional
    public void testFindByIdValid() {
        User user = randomUser();
        userRepository.save(user);

        User foundUser = userService.findById(user.getId());

        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals(user.getFirstName(), foundUser.getFirstName());
    }

    @Test
    @Transactional
    public void testFindByIdInvalid() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findById(Long.MIN_VALUE));
    }

    @Test
    @Transactional
    public void testUpdatedValid() {
        User user = randomUser();
        userRepository.save(user);

        User user2 = randomUser();

        userService.update(user2, user.getId());

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getFirstName(), foundUser.getFirstName());
    }

    @Test
    @Transactional
    public void testUpdatedInvalid() {
        User user = randomUser();
        userRepository.save(user);

        User user2 = new User();
        user2.setFirstName("");
        user2.setLastName("");

        Assertions.assertThrows(InvalidDataException.class, () -> userService.update(user2, user.getId()));

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getFirstName(), foundUser.getFirstName());
    }

    @Test
    @Transactional
    public void testDeleted() {
        User user = randomUser();
        userRepository.save(user);

        int before = userRepository.findAll().size();
        userService.delete(user.getId());
        int after = userRepository.findAll().size();

        User foundUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNull(foundUser);
        Assertions.assertEquals(before - 1, after);
    }

    private User randomUser() {
        User user = new User();
        user.setFirstName(UUID.randomUUID().toString().substring(0, 8));
        user.setLastName(UUID.randomUUID().toString().substring(0, 8));
        return user;
    }
}
