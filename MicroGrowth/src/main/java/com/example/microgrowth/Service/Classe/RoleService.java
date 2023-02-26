package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Role;
import com.example.microgrowth.DAO.Repositories.RoleRepository;
import com.example.microgrowth.Service.Interfaces.IRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService implements IRole {

    private RoleRepository roleRepository;
    @Override
    public Role add(Role a) {
        return roleRepository.save(a);
    }
}
