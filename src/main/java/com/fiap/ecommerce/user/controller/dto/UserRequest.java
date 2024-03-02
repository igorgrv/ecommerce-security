package com.fiap.ecommerce.user.controller.dto;

import com.fiap.ecommerce.user.entity.enums.UserRole;

public record UserRequest(String login, String password, UserRole role) {
}
