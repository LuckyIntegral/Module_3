package my.finances.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionPostModel {
    private String description;
    private Integer amount;
    private Long senderAccId;
    private Long receiverAccId;
}
