package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Role;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IRole;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleRestControllers {
    private IRole iRole;
    @PostMapping("/ajouterRole")

    public Role ajouter(@RequestBody Role role)
    {
        return iRole.add(role);
    }
}
