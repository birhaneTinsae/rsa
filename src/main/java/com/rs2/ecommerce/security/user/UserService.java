package com.rs2.ecommerce.security.user;


import com.rs2.ecommerce.exceptions.EntityNotFoundException;
import com.rs2.ecommerce.exceptions.PasswordMisMatchException;
import com.rs2.ecommerce.exceptions.UserAlreadyExistsException;
import com.rs2.ecommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService implements Common<User, User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private boolean usernameExist(String username) {
        return userRepository.findByUsername(username)
                .isPresent();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "Username", username));
    }

    public User passwordRest(UserPasswordDTO u) {
        var user = getByUsername(u.getUsername());
        if (user.getPassword() != null) {
            String hashedPassword = passwordEncoder.encode(u.getNewPassword());
            if (passwordEncoder.matches(u.getOldPassword(), user.getPassword())) {
                user.setPassword(hashedPassword);
            } else {
                throw new PasswordMisMatchException("Incorrect old password " + u.getOldPassword());
            }
        }
        return userRepository.save(user);
    }

    private User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "Username", username));
    }

    @Override
    public User store(@Valid User user) {
        if (usernameExist(user.getUsername())) {
            throw new UserAlreadyExistsException("There is an account with username '" + user.getUsername() + "'");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(!user.isEnabled() || user.isEnabled());
        user.setActive(!user.isActive() || user.isActive());
        user.setAccountNonExpired(!user.isAccountNonExpired() || user.isAccountNonExpired());
        user.setAccountNonLocked(!user.isAccountNonLocked() || user.isAccountNonLocked());
        user.setCredentialsNonExpired(!user.isCredentialsNonExpired() || user.isCredentialsNonExpired());
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> store(List<@Valid User> t) {
        return null;
    }

    @Override
    public User update(long id, User user) {
        return null;
    }

    @Override
    public User show(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, "id", String.valueOf(id)));
    }

    public User update(Long id, User userDto) {
        var user = show(id);
        user.setFullName(userDto.getFullName());
        if (userDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        user.setEnabled(userDto.isEnabled());
        user.setActive(userDto.isActive());
        user.setAccountNonExpired(userDto.isAccountNonExpired());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setCredentialsNonExpired(userDto.isCredentialsNonExpired());

        return userRepository.save(user);
    }

    @Override
    public boolean delete(long id) {
        var user = show(id);
        userRepository.save(user);
        return true;
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
