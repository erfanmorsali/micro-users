package com.example.microusers.modules.users.services;

import com.example.microusers.modules.users.dtos.UserInput;
import com.example.microusers.modules.users.dtos.UserOutput;
import com.example.microusers.modules.users.entities.User;
import com.example.microusers.modules.users.eventHandlers.UserDeletedEvent;
import com.example.microusers.modules.users.exceptions.NotUniqueUsernameException;
import com.example.microusers.modules.users.exceptions.UserNotFoundException;
import com.example.microusers.modules.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserService(UserRepository userRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.userRepository = userRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<UserOutput> getAllUsers() {
        return userRepository.findAll().stream().map(User::toUserOutput).collect(Collectors.toList());
    }


    public UserOutput getUserById(Long userId) throws UserNotFoundException {
        User user = userRepository.getUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("user not found");
        }
        return user.toUserOutput();
    }

    public UserOutput createUser(UserInput userInput) throws NotUniqueUsernameException {
        boolean isExists = userRepository.existsByUsername(userInput.getUsername());
        if (isExists) {
            throw new NotUniqueUsernameException("username is already taken");
        }
        User user = userInput.toUser();
        userRepository.save(user);
        return user.toUserOutput();
    }

    @Transactional
    public void deleteUserById(Long id) throws UserNotFoundException {
        boolean isExists = userRepository.existsById(id);
        if (!isExists) {
            throw new UserNotFoundException("user not found");
        }
        userRepository.deleteById(id);
        applicationEventPublisher.publishEvent(new UserDeletedEvent("user deleted", id));
    }

}
