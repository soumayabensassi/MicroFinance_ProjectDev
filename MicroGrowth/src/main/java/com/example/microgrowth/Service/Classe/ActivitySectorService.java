package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.ActivitySector;
import com.example.microgrowth.DAO.Repositories.ActivitySectorRepository;
import com.example.microgrowth.Service.Interfaces.IActivitySector;
import com.example.microgrowth.Service.Interfaces.IComment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ActivitySectorService implements IActivitySector {
    ActivitySectorRepository activitySectorRepository;
    @Override
    public ActivitySector add(ActivitySector activitySector) {
        return activitySectorRepository.save(activitySector);
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
}
