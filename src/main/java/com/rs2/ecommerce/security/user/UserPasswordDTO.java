package com.rs2.ecommerce.security.user;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String oldPassword;
    private String newPassword;
}
