package com.fiap.ecommerce.payment.service;

import org.springframework.stereotype.Service;

import com.fiap.ecommerce.payment.entity.Payment;
import com.fiap.ecommerce.user.entity.User;
import com.fiap.ecommerce.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserService userService;

    public void registerPayment(@Valid Payment request) {
        User user = userService.findById(request.userId());
        userService.cleanCart(user);
    }

}
