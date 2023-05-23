package my.finances.dto;

import my.finances.persistence.entity.User;

public record UserWithAccountNumberDTO(User user, int number) {
}
