package com.rs2.ecommerce.security.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDTO implements Serializable {
    private long id;
    private String username;
    private String fullName;
    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
}
