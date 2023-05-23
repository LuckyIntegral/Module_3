package my.finances.service;

import jakarta.persistence.EntityNotFoundException;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.Account;
import my.finances.persistence.entity.User;
import my.finances.persistence.repository.AccountRepository;
import my.finances.persistence.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void testCreateValidData() {
        User owner = createUser();
        Account account = createAccount();

        int before = accountRepository.findAll().size();
        accountService.create(account, owner.getId());
        int after = accountRepository.findAll().size();

        Assertions.assertEquals(before + 1, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidData() {
        User owner = createUser();
        Account account = new Account();
        account.setBalance(Integer.MIN_VALUE);
        account.setName("");

        int before = accountRepository.findAll().size();
        Assertions.assertThrows(InvalidDataException.class, () -> accountService.create(account, owner.getId()));
        int after = accountRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidOwnerData() {
        Account account = new Account();
        account.setBalance(Integer.MIN_VALUE);
        account.setName("");

        int before = accountRepository.findAll().size();
        Assertions.assertThrows(InvalidDataException.class, () -> accountService.create(account, Long.MIN_VALUE));
        int after = accountRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testUpdatedValidData() {
        User owner = createUser();
        Account account = createAccount();

        int before = accountRepository.findAll().size();
        accountService.create(account, owner.getId());
        int after = accountRepository.findAll().size();
        Assertions.assertEquals(before + 1, after);

        Account account2 = createAccount();
        accountService.update(account2, account.getId());

        Optional<Account> found = accountRepository.findById(account2.getId());
        Assertions.assertTrue(found.isPresent());
        Assertions.assertEquals(account2.getName(), found.get().getName());
        Assertions.assertEquals(after, accountService.findAll().size());
    }

    @Test
    @Transactional
    public void testUpdatedInvalidData() {
        User owner = createUser();
        Account account = createAccount();

        int before = accountRepository.findAll().size();
        accountService.create(account, owner.getId());
        int after = accountRepository.findAll().size();
        Assertions.assertEquals(before + 1, after);

        Account account2 = new Account();
        account2.setName("");
        account2.setBalance(0);
        Assertions.assertThrows(InvalidDataException.class, () -> accountService.update(account2, account.getId()));

        Optional<Account> found = accountRepository.findById(account2.getId());
        Assertions.assertTrue(found.isEmpty());
        Assertions.assertEquals(account.getName(), accountRepository.findById(account.getId()).get().getName());
    }

    @Test
    @Transactional
    public void testFindById() {
        User owner = createUser();
        Account account = createAccount();
        accountService.create(account, owner.getId());

        Assertions.assertNotNull(accountService.findById(account.getId()));
        Assertions.assertEquals(account.getName(), accountService.findById(account.getId()).getName());
    }

    @Test
    @Transactional
    public void testFindByIdInvalid() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.findById(Long.MIN_VALUE));
    }

    @Test
    @Transactional
    public void testFindAll() {
        int before = accountService.findAll().size();

        User owner1 = createUser();
        Account account1 = createAccount();
        accountService.create(account1, owner1.getId());

        User owner2 = createUser();
        Account account2 = createAccount();
        accountService.create(account2, owner2.getId());

        int after = accountService.findAll().size();

        Assertions.assertEquals(before + 2, after);
    }

    @Test
    @Transactional
    public void testDelete() {
        int before = accountService.findAll().size();

        User owner1 = createUser();
        Account account1 = createAccount();
        accountService.create(account1, owner1.getId());

        int after = accountService.findAll().size();
        Assertions.assertEquals(before + 1, after);

        accountService.delete(account1.getId());
        int after2 = accountService.findAll().size();

        Assertions.assertEquals(after - 1, after2);
    }

    @Test
    @Transactional
    public void testDeleteInvalid() {
        int before = accountService.findAll().size();

        Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.delete(Long.MIN_VALUE));

        int after = accountService.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testFindByUserIdValidData() {
        User user = createUser();
        accountService.create(createAccount(), user.getId());
        accountService.create(createAccount(), user.getId());
        accountService.create(createAccount(), user.getId());

        Assertions.assertEquals(accountService.findByUserId(user.getId()).size(), 3);
    }

    @Test
    @Transactional
    public void testFindByUserIdInvalidData() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.findByUserId(Long.MIN_VALUE));
    }

    private Account createAccount() {
        Random random = new Random();
        Account account = new Account();
        account.setName(UUID.randomUUID().toString().substring(0, 8));
        account.setBalance(random.nextInt(0, Integer.MAX_VALUE));
        return account;
    }

    private User createUser() {
        User user = new User();
        user.setFirstName(UUID.randomUUID().toString().substring(0, 8));
        user.setLastName(UUID.randomUUID().toString().substring(0, 8));
        userRepository.save(user);
        return user;
    }
}
