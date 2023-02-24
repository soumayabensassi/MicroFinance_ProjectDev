package com.example.microgrowth.RestControllers;


import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.Service.Interfaces.ITrainingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class TrainingRestController {
    private ITrainingService iTrainingService;
    @GetMapping("/afficherT")
    public List<Training> afficherT(){
        return
                iTrainingService.selectAll();
    }
    @PostMapping("/ajouterTraining")
    public Training ajouterT(@RequestBody Training training){
        return iTrainingService.add(training);
    }
    @GetMapping("/afficherAvecIdT/{idtraining}")
    public Training afficherAvecIdT(@PathVariable int idtraining){
        return iTrainingService.selectById(idtraining);
    }
    @DeleteMapping("/deleteTraining/{idtraining}")
    public void supprimerAvecIdT(@PathVariable int idtraining){
        iTrainingService.deleteById(idtraining);
    }
    @DeleteMapping("/deleteTraining")
    public void supprimerT(Training training){
        iTrainingService.delete(training);
    }
    @PutMapping("/editTraining")
    public Training modifier(@RequestBody Training training)
    {return iTrainingService.edit(training);
    }

}
