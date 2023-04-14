package my.finances.dto;

import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.entity.Transaction;

import java.util.Date;

@Getter
@Setter
public class TransactionDetails {
    private Long id;
    private Date created;
    private String description;
    private Integer amount;
    private String transactionType;
    private Long accId;
    private String accName;
    private Long ownerId;
    private String ownerName;

    public TransactionDetails(Transaction transaction) {
        this.id = transaction.getId();
        this.created = transaction.getCreated();
        this.description = transaction.getDescription();
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType().toString();
        this.accId = transaction.getAccount().getId();
        this.accName = transaction.getAccount().getName();
        this.ownerId = transaction.getAccount().getOwner().getId();
        this.ownerName = transaction.getAccount().getOwner().getFirstName()
                + " " + transaction.getAccount().getOwner().getLastName();
    }
}
