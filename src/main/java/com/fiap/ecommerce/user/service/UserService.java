package com.fiap.ecommerce.user.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fiap.ecommerce.cart.entity.Cart;
import com.fiap.ecommerce.cart.repository.CartRepository;
import com.fiap.ecommerce.exception.AlreadyExistsException;
import com.fiap.ecommerce.exception.NotFoundException;
import com.fiap.ecommerce.security.TokenService;
import com.fiap.ecommerce.user.controller.dto.UserAuthRequest;
import com.fiap.ecommerce.user.controller.dto.UserRequest;
import com.fiap.ecommerce.user.controller.dto.UserTokenResponse;
import com.fiap.ecommerce.user.entity.User;
import com.fiap.ecommerce.user.repository.UserRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final CartRepository cartRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("Could not find any user given id: " + userId));
    }

    public UserDetails findByLogin(String login) {
        return repository.findByLogin(login);
    }

    public void registerUser(@Valid UserRequest userRequest) {
        if (repository.findByLogin(userRequest.login()) != null)
            throw new AlreadyExistsException("Login already exists");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRequest.password());
        User newUser = new User(userRequest, encryptedPassword);
        Cart cart = new Cart(newUser);
        newUser.setCart(cart);

//        cartRepository.save(cart);
        repository.save(newUser);
    }

    public UserTokenResponse login(UserAuthRequest data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return new UserTokenResponse(token);
    }

    // public User update(String id, UserDto user) {
    // User oldUser = findById(id);
    // User updatedUser = user.getUserUpdated(oldUser);
    // return repository.save(updatedUser);
    // }

    public void delete(String id) {
        findById(id);
        repository.deleteById(id);
    }

    public void save(User user) {
        repository.save(user);
    }
}
