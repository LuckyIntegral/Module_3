package my.finances.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWithAccNumberModel {
    private UserModel user;
    private Integer number;
}
