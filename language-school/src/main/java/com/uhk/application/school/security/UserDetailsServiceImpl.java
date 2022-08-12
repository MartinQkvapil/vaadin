package com.uhk.application.school.security;

import com.uhk.application.school.data.entity.User;
import com.uhk.application.school.data.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            org.springframework.security.crypto.password.PasswordEncoder encoder
                    = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
            encoder.encode("admin");
            System.out.println(encoder.encode("admin"));
            System.out.println(encoder.encode("user"));
            System.out.println(user.getHashedPassword());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getHashedPassword(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(User user) {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        return list;

    }

}
