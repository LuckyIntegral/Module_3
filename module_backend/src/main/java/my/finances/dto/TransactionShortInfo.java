package my.finances.dto;

import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.entity.Transaction;

@Getter
@Setter
public class TransactionShortInfo {
    private Long id;
    private Integer amount;
    private String type;
    private String accName;

    public TransactionShortInfo(Transaction transaction) {
        this.id = transaction.getId();
        this.amount = transaction.getAmount();
        this.type = transaction.getTransactionType().toString();
        this.accName = transaction.getAccount().getName();
    }
}
