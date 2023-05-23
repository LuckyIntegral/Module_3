package my.finances.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionCreatedDTO {
    private String description;
    private Integer amount;
    private Long senderAccId;
    private Long receiverAccId;
}
