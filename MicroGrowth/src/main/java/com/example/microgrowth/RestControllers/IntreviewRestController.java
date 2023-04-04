package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Intreview;
import com.example.microgrowth.Service.Interfaces.IIntreview;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class IntreviewRestController {
    IIntreview iIntreview;
    @PostMapping("/AffecterIntreviewCredit/{id}")
    public Intreview add_intreview(@PathVariable int idcredit,@RequestBody Intreview intreview)
    {
        return iIntreview.add_intreview(idcredit,intreview);
    }
}
