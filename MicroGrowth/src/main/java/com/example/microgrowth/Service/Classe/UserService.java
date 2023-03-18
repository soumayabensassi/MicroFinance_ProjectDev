package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Role;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RoleRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService implements IUser {
    private final PasswordEncoder passwordEncoder;
   @Autowired
    private UserRepository userRepository;
   private RoleRepository roleRepository;
    @Override
    public void add(User a) {
       a.setPassword(passwordEncoder.encode(a.getPassword()));
       a.setActive(false);
       Role r= roleRepository.findById(1).get();
       a.setRoles(r);
         userRepository.save(a);
    }

    @Override
    public User edit(User a) {
        return userRepository.save(a);
    }

    @Override
    public List<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    public User SelectById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
    userRepository.deleteById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
