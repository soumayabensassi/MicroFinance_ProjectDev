package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.ActivitySector;
import com.example.microgrowth.Service.Interfaces.IActivitySector;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class ActivitySectorRestControllers {
    private IActivitySector iActivitySector;
    @GetMapping("/admin/afficherActivitySectors")
    public List<ActivitySector> afficher()
    {
        return iActivitySector.selectAll();
    }
    @GetMapping("afficherActivitySectors")
    public List<ActivitySector> afficher1()
    {
        return iActivitySector.selectAll();
    }
    @PostMapping("/admin/ajouterActivitySector")

    public ActivitySector ajouter(@RequestBody ActivitySector activitySector)
    {
        return iActivitySector.add(activitySector);
    }
    @PutMapping("/admin/updateActivitySector")
    public ActivitySector update(@RequestBody ActivitySector activitySector)
    {return iActivitySector.edit(activitySector);
    }
    @GetMapping("/admin/AfficherActivitySectorbyID/{id}")
    public ActivitySector AfficherByID(@PathVariable int id)
    {
        return iActivitySector.SelectById(id);
    }
    @DeleteMapping("/admin/deleteActivitySector/{id}")
    public void delete(@PathVariable int id)
    {
        iActivitySector.deleteById(id);
    }
}
