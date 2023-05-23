package my.finances.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import my.finances.persistence.types.TransactionType;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity {
    @Column
    private String description;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @ManyToOne
    private Account account;
    public Transaction() {
        super();
    }
}
