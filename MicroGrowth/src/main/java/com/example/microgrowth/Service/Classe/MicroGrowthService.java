package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Role;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RoleRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class MicroGrowthService implements IMicroGrowth,UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepo;
    @Autowired
    RoleRepository rolesRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(email);
        System.out.println(user.isActive());
        if (user.isActive()==true) {
            if (user == null) {
                log.error("user not found in the database");
            } else {
                log.info("user found in the database: {}", email);
            }
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRoles().getName()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
        else {
            throw new RuntimeException("user is disabled");
        }
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return rolesRepo.save(role);
    }

//    @Override
//    public void AddRoleToUser(String username, String roleName) {
//        User user = userRepo.findByEmail(username);
//        Role role= rolesRepo.findByName(roleName);
//        user.getRoles().add(role);
//    }


    @Override
    public User getUser(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }



}