package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.ActivitySector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivitySectorRepository extends JpaRepository<ActivitySector,Integer> {

    @Query("SELECT a.interestRate FROM ActivitySector a WHERE a.idSecteur = :idS")
    float getInterestRateByIdSecteur(@Param("idS") int idS);

    @Query("SELECT a.correlationRatio FROM ActivitySector a WHERE a.idSecteur = :idS")
    float getCorrelationRatioByIdSecteur(@Param("idS") int idS);
}
