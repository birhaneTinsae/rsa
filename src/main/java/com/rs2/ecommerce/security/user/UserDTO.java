package com.rs2.ecommerce.security.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
    private long id;
    @NotNull(message="Username is required.")
    private String username;
    private String password;
    private String fullName;
    @Email
    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
}
