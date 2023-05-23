package my.finances.service;

import jakarta.persistence.EntityNotFoundException;
import my.finances.dto.TransactionCreatedDTO;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.Account;
import my.finances.persistence.entity.User;
import my.finances.persistence.repository.AccountRepository;
import my.finances.persistence.repository.TransactionRepository;
import my.finances.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionService transactionService;

    @Test
    @Transactional
    public void testCreateValidData() {
        Account sender = createAccount();
        Account receiver = createAccount();

        TransactionCreatedDTO transaction = new TransactionCreatedDTO();

        transaction.setReceiverAccId(receiver.getId());
        transaction.setSenderAccId(sender.getId());
        transaction.setAmount(500);
        transaction.setDescription("Test");

        int before = transactionRepository.findAll().size();
        transactionService.create(transaction);
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(before + 2, after); // Each transaction is duplicated, so it is correct :)
    }

    @Test
    @Transactional
    public void testCreateInvalidUserData() {
        TransactionCreatedDTO transaction = new TransactionCreatedDTO();

        transaction.setReceiverAccId(Long.MAX_VALUE);
        transaction.setSenderAccId(Long.MAX_VALUE);
        transaction.setAmount(500);
        transaction.setDescription("Test");

        int before = transactionRepository.findAll().size();
        Assertions.assertThrows(EntityNotFoundException.class, () -> transactionService.create(transaction));
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidUserDuplicatedData() {
        Account sender = createAccount();

        TransactionCreatedDTO transaction = new TransactionCreatedDTO();

        transaction.setReceiverAccId(sender.getId());
        transaction.setSenderAccId(sender.getId());
        transaction.setAmount(500);
        transaction.setDescription("Test");

        int before = transactionRepository.findAll().size();
        Assertions.assertThrows(InvalidDataException.class, () -> transactionService.create(transaction));
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidData() {
        Account receiver = createAccount();
        Account sender = createAccount();

        TransactionCreatedDTO transaction = new TransactionCreatedDTO();

        transaction.setReceiverAccId(receiver.getId());
        transaction.setSenderAccId(sender.getId());
        transaction.setAmount(-500); // Positive values only
        transaction.setDescription("Test");

        int before = transactionRepository.findAll().size();
        Assertions.assertThrows(InvalidDataException.class, () -> transactionService.create(transaction));
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testCreateInvalidDataInsufficientFunds() {
        Account receiver = createAccount();
        Account sender = createAccount();

        TransactionCreatedDTO transaction = new TransactionCreatedDTO();

        transaction.setReceiverAccId(receiver.getId());
        transaction.setSenderAccId(sender.getId());
        transaction.setAmount(Integer.MAX_VALUE); // Insufficient funds
        transaction.setDescription("Test");

        int before = transactionRepository.findAll().size();
        Assertions.assertThrows(InvalidDataException.class, () -> transactionService.create(transaction));
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(before, after);
    }

    @Test
    @Transactional
    public void testFindByAccountIdValidData() {
        Account receiver = createAccount();

        TransactionCreatedDTO transaction = new TransactionCreatedDTO();
        TransactionCreatedDTO transaction2 = new TransactionCreatedDTO();

        transaction.setReceiverAccId(receiver.getId());
        transaction.setSenderAccId(createAccount().getId());
        transaction.setAmount(500);
        transaction.setDescription("Test");


        transaction2.setReceiverAccId(receiver.getId());
        transaction2.setSenderAccId(createAccount().getId());
        transaction2.setAmount(400);
        transaction2.setDescription("Test 2");

        int rtb = transactionService.findAllByAccountId(receiver.getId()).size();
        int before = transactionRepository.findAll().size();
        transactionService.create(transaction);
        transactionService.create(transaction2);
        int rta = transactionService.findAllByAccountId(receiver.getId()).size();
        int after = transactionRepository.findAll().size();

        Assertions.assertEquals(rtb + 2, rta);
        Assertions.assertEquals(before + 4, after);
    }

    private Account createAccount() {
        Random random = new Random();
        Account account = new Account();
        account.setName(UUID.randomUUID().toString().substring(0, 8));
        account.setBalance(random.nextInt(1_000, 10_000));
        account.setOwner(createUser());
        accountRepository.save(account);
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