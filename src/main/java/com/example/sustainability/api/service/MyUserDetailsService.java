package com.example.sustainability.api.service;//package com.example.sustainability.api.service;
//
//import com.example.sustainability.api.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public User loadUserByUsername(String email)  {
//        Optional<com.example.sustainability.api.data.User> user = userRepository.findByEmail(email);
//        if (user.isEmpty()) {
//            try {
//                throw new Exception("No user found with username: " + email);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        com.example.sustainability.api.data.User existingUser = user.get();
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//
//        return new org.springframework.security.core.userdetails.User(
//                existingUser.getEmail(), existingUser.getPassword(), enabled, accountNonExpired,
//                credentialsNonExpired, accountNonLocked, getAuthorities(Collections.singletonList(existingUser.getRole())));
//    }
//
//    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
//}
