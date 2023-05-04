package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Intreview;
import com.example.microgrowth.Service.Interfaces.IIntreview;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
public class IntreviewRestController {
    IIntreview iIntreview;
    @PostMapping("/admin/AffecterIntreviewCredit/{idcredit}")
    public Intreview add_intreview(@PathVariable int idcredit,@RequestBody Intreview intreview)
    {
        return iIntreview.add_intreview(idcredit,intreview);
    }
    @GetMapping("/admin/AffichetIntreview")
    public List<Intreview> affich()
    {
        return iIntreview.ListIntreview();
    }
}
