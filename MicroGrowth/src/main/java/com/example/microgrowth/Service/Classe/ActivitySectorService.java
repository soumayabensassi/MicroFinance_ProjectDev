package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.ActivitySector;
import com.example.microgrowth.DAO.Repositories.ActivitySectorRepository;
import com.example.microgrowth.Service.Interfaces.IActivitySector;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ActivitySectorService implements IActivitySector {
    @Autowired
    ActivitySectorRepository activitySectorRepository;

    @Override
    public ActivitySector add(ActivitySector activitySector) {
        String message="le nom du secteur doit etre qu'on lettre.";
        if(name_control(activitySector.getName())==false){
           return   activitySectorRepository.save(activitySector);
        }
        System.out.println(message);
        return null;
    }

    @Override
    public ActivitySector edit(ActivitySector activitySector) {
        return activitySectorRepository.save(activitySector);
    }

    @Override
    public List<ActivitySector> selectAll() {
        return activitySectorRepository.findAll();
    }

    @Override
    public ActivitySector SelectById(int id_sector) {
        return activitySectorRepository.findById(id_sector).get();
    }

    @Override
    public void deleteById(int id_sector) {
activitySectorRepository.deleteById(id_sector);
    }

    @Override
    public boolean name_control(String name) {
        for(int i=0;i<name.length();i++){
            if(Character.isDigit(name.charAt(i))){
                return true;
            }
    }
        return false;


}

}
