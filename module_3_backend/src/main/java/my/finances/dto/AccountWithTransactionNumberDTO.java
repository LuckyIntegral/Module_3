package my.finances.dto;

import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.entity.Account;

import java.util.Date;

@Getter
@Setter
public class AccountWithTransactionNumberDTO {
    private Long accId;
    private Date accCreated;
    private Integer balance;
    private String name;
    private Long userId;
    private Integer transactionNumber;

    public AccountWithTransactionNumberDTO(Account account, int number) {
        this.accId = account.getId();
        this.accCreated = account.getCreated();
        this.balance = account.getBalance();
        this.name = account.getName();
        this.userId = account.getOwner().getId();
        this.transactionNumber = number;
    }
}
