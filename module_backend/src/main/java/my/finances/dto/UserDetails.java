package my.finances.dto;

import my.finances.persistence.entity.User;

import java.util.Collection;

public record UserDetails(User user, Collection<AccountShortInfo> accounts) {
}
