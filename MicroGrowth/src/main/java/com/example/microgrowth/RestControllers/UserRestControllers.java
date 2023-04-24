package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.ImageModel;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.ImageModelRepository;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserRestControllers {
    private IUser iUser;
    private IMicroGrowth iMicroGrowth;
    private EmailService emailService;
    ImageModelRepository imageModelRepository;
    @GetMapping("/admin/afficheruser")
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
    {   boolean testMotdepasse=user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+~`|}{\\[\\]\\\\:;'<>,.?/\\-])[A-Za-z0-9!@#$%^&*()_+~`|}{\\[\\]\\\\:;'<>,.?/\\-]{8,}$");
        boolean test = hasEightDigits(user.getPhone());
        boolean testCin = hasEightDigits(user.getCin());
        if(!test)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le numèro de téléphone doit contenir 8 chiffres");
        } else if (!testCin) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le numèro de CIN doit contenir 8 chiffres");

        } else if (!user.getEmail().matches("^.+@.+\\..+$")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("un problème au niveau de saise du mail");

        } else if (!user.getVerifPassword().matches(user.getPassword()) || !testMotdepasse) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("verifiez le mot de passe ");

        }
        else
            //(user.getEmail().matches("^.+@.+\\..+$") && user.getVerifPassword().matches(user.getPassword()) && test==true )
        {iUser.add(user);
            emailService.ConfirmeCompte(user.getEmail());
        return  ResponseEntity.status(HttpStatus.OK).body("ajout done");
        }


    }
    @PutMapping("/user/updateuser")
    public User update(@RequestBody User user)
    {return iUser.edit(user);
    }
    @GetMapping("/afficherUserbyID/{id}")
    public User AfficherByID(@PathVariable int id)
    {
        return iUser.SelectById(id);
    }
    @GetMapping("/AfficherUserByemail/{email}")
    public User AfficherByemail(@PathVariable String email)
    {
        return iUser.getUserByEmail(email);
    }
    @DeleteMapping("/admin/deleteUserbyID/{id}")
    public void delete(@PathVariable int id)
    {
        iUser.deleteById(id);
    }
    @PostMapping("/ConfirmeCompte/{email}")
    public void Confirme(@PathVariable String email)
    {
        User u=iUser.getUserByEmail(email);
        u.setActive(true);
        iUser.edit(u);
    }
    @GetMapping("/session")
    public User getUSerCnnetcte()
    {
        return iUser.getUserByEmail( iMicroGrowth.getCurrentUserName());
    }
    @PutMapping("/user/updateuser/{id}")
    public void updateuserbyID(@PathVariable String id,@RequestBody User user)
    {
        User u=iUser.getUserByEmail(id);
        u.setFirstName(user.getFirstName());
        u.setLasttName(user.getLasttName());
        u.setCin(user.getCin());
        u.setEmail(user.getEmail());
        u.setPhone(user.getPhone());
        u.setProfession(user.getProfession());
        iUser.edit(u);
    }
    public ImageModel uploadimage(MultipartFile multipartFile) throws IOException
    {

        ImageModel imageModel=new ImageModel(
                multipartFile.getOriginalFilename(),multipartFile.getContentType(),multipartFile.getBytes());
        imageModelRepository.save(imageModel);
        return imageModel;
    }
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "/upload-image",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public User uploadimageuser(@RequestPart("User") User user,@RequestPart("image") MultipartFile file)
    {try{

        user.setImageModel(uploadimage(file));
        System.out.println(user.getPassword());
        iUser.edit(user);
        return user;
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        return null;
    }
    }
}
