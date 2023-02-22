package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserRestControllers {
    private IUser iUser;
    @GetMapping("/afficheruser")
    public List<User> afficher()
    {
        return iUser.selectAll();
    }
    @PostMapping("/ajouteruser")

    public User ajouter(@RequestBody User user)
    {
        return iUser.add(user);
    }
    @PutMapping("/updateuser")
    public User update(@RequestBody User user)
    {return iUser.edit(user);
    }
    @GetMapping("/afficherUserbyID/{id}")
    public User AfficherByID(@PathVariable int id)
    {
        return iUser.SelectById(id);
    }
    @DeleteMapping("/deleteUserbyID/{id}")
    public void delete(@PathVariable int id)
    {
        iUser.deleteById(id);
    }
}
