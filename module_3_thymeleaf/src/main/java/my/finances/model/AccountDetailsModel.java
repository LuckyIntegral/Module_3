package my.finances.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
public class AccountDetailsModel {
    private Long accId;
    private Date accCreated;
    private Integer balance;
    private String name;
    private Long userId;
    private Collection<TransactionModel> transactions;
}
