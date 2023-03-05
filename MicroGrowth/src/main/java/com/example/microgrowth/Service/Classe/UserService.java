package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService implements IUser {
    private UserRepository userRepository;
    @Override
    public void add(User a) {

    }

    @Override
    public User edit(User a) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User SelectById(int id) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public User getUserByEmail(String email) {
         return userRepository.findByEmail(email);
    }
}
