package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService implements IUser {
    private UserRepository userRepository;
    @Override
    public User add(User a) {
        return userRepository.save(a);
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
}
