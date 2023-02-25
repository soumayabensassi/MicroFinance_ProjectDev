package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.ActivitySector;

import java.util.List;

public interface IActivitySector {
    ActivitySector add(ActivitySector activitySector);
    ActivitySector edit(ActivitySector activitySector);
    List<ActivitySector> selectAll();
    ActivitySector SelectById(int id_sector);
    void deleteById(int id_sector);
}
