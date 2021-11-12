package com.example.microusers.modules.users.dtos;

import com.example.microusers.modules.users.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
    @NotNull
    private String username;

    public User toUser() {
        return new User(username);
    }
}
