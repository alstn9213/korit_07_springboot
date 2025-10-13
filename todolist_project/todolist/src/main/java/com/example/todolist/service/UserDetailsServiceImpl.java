package com.example.todolist.service;

import com.example.todolist.domain.AppUser;
import com.example.todolist.domain.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.withUsername;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);
        User.UserBuilder builder = null;
        if(user.isPresent()) {
            AppUser currentUser = user.get(); // user 자체는 Optional 자료형이지 AppUser가 아니다.
            builder = User.withUsername(username);
            builder.password(currentUser.getPassword()).roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
        return builder.build();
    }
}
