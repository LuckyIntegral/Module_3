package my.finances.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserModel {
    private Long id;
    private Date created;
    private String firstName;
    private String lastName;
}
