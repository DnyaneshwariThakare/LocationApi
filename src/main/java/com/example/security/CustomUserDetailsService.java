package com.example.security;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByUsername(username));

        User user = userOpt.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Here you can encode the password if you're not already storing it in an encoded form
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(), // Ensure this password is encoded before storing it in the DB
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] roles = user.getRoles();
        for (int i = 0; i < roles.length; i++) {
            if (!roles[i].startsWith("ROLE_")) {
                roles[i] = "ROLE_" + roles[i];
            }
        }
        return AuthorityUtils.createAuthorityList(roles);
    }
}
