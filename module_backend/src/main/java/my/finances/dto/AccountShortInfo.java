package my.finances.dto;

import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.entity.Account;

@Getter
@Setter
public class AccountShortInfo {
    private Long id;
    private Integer balance;
    private String name;
    private Integer transactionNumber;
    private String owner;

    public AccountShortInfo(Account account, Integer number) {
        this.id = account.getId();
        this.balance = account.getBalance();
        this.name = account.getName();
        this.transactionNumber = number;
        this.owner = account.getOwner().getFirstName() + " " + account.getOwner().getLastName();
    }
}
