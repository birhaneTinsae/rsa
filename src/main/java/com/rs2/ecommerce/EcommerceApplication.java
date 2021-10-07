package com.rs2.ecommerce;

import com.rs2.ecommerce.security.user.User;
import com.rs2.ecommerce.security.user.UserRepository;
import com.rs2.ecommerce.utils.ApplicationProps;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Optional;

@SpringBootApplication
@Log4j2
@EnableConfigurationProperties(value ={  ApplicationProps.class})
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Component
    @RequiredArgsConstructor
    static class CreatUser implements CommandLineRunner {
        private final UserRepository repository;
        private final PasswordEncoder encoder;

        @Override
        public void run(String... args) {
            Optional<User> admin = repository.findByUsername("Admin");
            if (admin.isEmpty()) {
                var user = User.builder()
                        .username("Admin")
                        .password(encoder.encode("6DKgfJLT56de6jFp"))
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .active(true)
                        .credentialsNonExpired(true)
                        .build();

                repository.save(user);
            }

            SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            log.info(Encoders.BASE64.encode(key.getEncoded()));

        }

    }
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper=   new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return  modelMapper;
    }
}
