package com.fiap.ecommerce.user.controller.dto;

import com.fiap.ecommerce.user.entity.User;

public record UserResponse (String id, String fullname, String login) {

  public static UserResponse fromEntity(User user) {
    return new UserResponse(user.getId(), user.getFullName(), user.getLogin());
  }
}
