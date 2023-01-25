package pl.krzysztofwywial.config;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MemoryUserDetailsManager extends InMemoryUserDetailsManager {

    public MemoryUserDetailsManager() {
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder.username("root").password("{noop}123").roles("ADMIN").build();
        createUser(admin);
    }
}
