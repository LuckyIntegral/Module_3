package my.finances.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionModel {
    private Long id;
    private Integer amount;
    private String type;
    private String accName;
}
