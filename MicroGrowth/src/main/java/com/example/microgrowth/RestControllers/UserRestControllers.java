package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/MicroGrowth/user")
public class UserRestControllers {
    private IUser iUser;
    private IMicroGrowth iMicroGrowth;
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

    public ResponseEntity<String> ajouter(@RequestBody User user)
    {
        boolean test = hasEightDigits(user.getPhone());
        if(!test)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le numèro de téléphone doit contenir 8 chiffres");
        } else if (!user.getEmail().matches("^.+@.+\\..+$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("un problème au niveau de saise du mail");

        } else if (!user.getVerifPassword().matches(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("verifiez le mot de passe ");

        }
        else
            //(user.getEmail().matches("^.+@.+\\..+$") && user.getVerifPassword().matches(user.getPassword()) && test==true )
        {iUser.add(user);
        return  ResponseEntity.status(HttpStatus.OK).body("ajout done");
        }


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
