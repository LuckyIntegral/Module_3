package my.finances.dto;

import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.entity.Transaction;

import java.util.Date;

@Getter
@Setter
public class TransactionDetails extends TransactionShortInfo {
    private Date created;
    private String description;
    private Long accId;
    private Long ownerId;
    private String ownerName;

    public TransactionDetails(Transaction transaction) {
        super(transaction);
        this.created = transaction.getCreated();
        this.description = transaction.getDescription();
        this.accId = transaction.getAccount().getId();
        this.ownerId = transaction.getAccount().getOwner().getId();
        this.ownerName = transaction.getAccount().getOwner().getFirstName()
                + " " + transaction.getAccount().getOwner().getLastName();
    }
}
