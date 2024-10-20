package ru.perminov.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.perminov.config.MyUserDetails;
import ru.perminov.models.Role;
import ru.perminov.models.User;
import ru.perminov.repository.RoleRepository;
import ru.perminov.repository.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AppService implements UserDetailsService {
     private UserRepository repository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;


    @Transactional
    public void addUser(User user) {
        for(Role r : user.getRoles()) {
            roleRepository.save(r);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
