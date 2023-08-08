package com.store.orders.dtos;

import com.store.orders.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
