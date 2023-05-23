package my.finances.model;

import lombok.Setter;
import lombok.Getter;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsModel {
    private UserModel user;
    private Collection<AccountModel> accounts;
}
