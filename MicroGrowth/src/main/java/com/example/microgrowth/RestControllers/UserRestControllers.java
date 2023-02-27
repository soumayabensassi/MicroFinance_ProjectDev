package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public boolean hasEightDigits(Long number) {
        return (number >= 10000000L && number <= 99999999L);//La première condition vérifie si "number" est supérieur ou égal à 10^7 (ce qui correspond à un nombre à 8 chiffres ou plus).
        //La deuxième condition vérifie si "number" est inférieur ou égal à 10^8 - 1 (ce qui correspond à un nombre à 8 chiffres ou moins).
    }
    @PostMapping("/ajouteruser")

    public User ajouter(@RequestBody User user)
    {
        boolean test = hasEightDigits(user.getPhone());
        if (user.getEmail().matches("^.+@.+\\..+$") && user.getVerifPassword().matches(user.getPassword()) && test==true )
        return iUser.add(user);

        else
            return iUser.SelectById(user.getIdUser());
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
