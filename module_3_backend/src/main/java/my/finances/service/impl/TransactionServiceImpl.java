package my.finances.service.impl;

import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;

import my.finances.dto.TransactionCreatedDTO;
import my.finances.exception.InvalidDataException;
import my.finances.persistence.entity.Account;
import my.finances.persistence.entity.Transaction;
import my.finances.persistence.repository.AccountRepository;
import my.finances.persistence.repository.TransactionRepository;
import my.finances.persistence.types.TransactionType;
import my.finances.service.TransactionService;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Objects;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public void create(TransactionCreatedDTO transactionDTO) {
        validateTransaction(transactionDTO);
        Integer senderBalance = accountRepository.findById(transactionDTO.getSenderAccId()).get().getBalance();
        Integer receiverBalance = accountRepository.findById(transactionDTO.getReceiverAccId()).get().getBalance();
        Account sender = accountRepository.findById(transactionDTO.getSenderAccId())
                .orElseThrow(() -> new EntityNotFoundException("Check your data and try again"));
        Account receiver = accountRepository.findById(transactionDTO.getReceiverAccId())
                .orElseThrow(() -> new EntityNotFoundException("Check your data and try again"));
        Transaction transactionSender = new Transaction();
        transactionSender.setAccount(sender);
        transactionSender.setDescription(transactionDTO.getDescription());
        transactionSender.setTransactionType(TransactionType.EXPENSE);
        transactionSender.setAmount(transactionDTO.getAmount());
        Transaction transactionReceiver = new Transaction();
        transactionReceiver.setAccount(receiver);
        transactionReceiver.setTransactionType(TransactionType.PROFIT);
        transactionReceiver.setAmount(transactionDTO.getAmount());
        transactionReceiver.setDescription("Replenishment from " +
                sender.getOwner().getFirstName() + " " +
                sender.getOwner().getLastName());
        sender.setBalance(senderBalance - transactionDTO.getAmount());
        receiver.setBalance(receiverBalance + transactionDTO.getAmount());
        accountRepository.save(sender);
        accountRepository.save(receiver);
        transactionRepository.save(transactionSender);
        transactionRepository.save(transactionReceiver);
    }

    private void validateTransaction(TransactionCreatedDTO transactionDTO) {
        if (transactionDTO.getSenderAccId() == null || transactionDTO.getReceiverAccId() == null) {
            throw new EntityNotFoundException("Id == null");
        }
        if (accountRepository.findById(transactionDTO.getSenderAccId()).isEmpty()) {
            throw new EntityNotFoundException("Sender does not exist");
        }
        if (accountRepository.findById(transactionDTO.getReceiverAccId()).isEmpty()) {
            throw new EntityNotFoundException("Receiver does not exist");
        }
        if (Objects.equals(transactionDTO.getReceiverAccId(), transactionDTO.getSenderAccId())) {
            throw new InvalidDataException("Invalid data");
        }
        if (transactionDTO.getAmount() <= 0) {
            throw new InvalidDataException("Invalid suma");
        }
        if (accountRepository.findById(transactionDTO.getSenderAccId()).get().getBalance() < transactionDTO.getAmount()) {
            throw new InvalidDataException("Insufficient funds");
        }
    }

    @Override
    @Transactional
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exist"));
    }

    @Override
    @Transactional
    public Collection<Transaction> findAllByAccountId(long id) {
        return transactionRepository.findAllByAccountId(id);
    }

    @Override
    @Transactional
    public Collection<Transaction> findAll() {
        return transactionRepository.findAll();
    }
}
