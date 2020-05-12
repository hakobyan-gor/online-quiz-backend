package com.quiz.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import com.quiz.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.quiz.security.UserPrincipal;
import com.quiz.models.User;

@Service("UserDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(userName);
        if (user == null)
            throw new UsernameNotFoundException("Username not found");

        return new UserPrincipal(user);
    }

}
