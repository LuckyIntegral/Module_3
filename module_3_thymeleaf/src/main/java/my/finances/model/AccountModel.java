package my.finances.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountModel {
    private Long id;
    private Integer balance;
    private String name;
    private Integer transactionNumber;
    private String owner;
}
