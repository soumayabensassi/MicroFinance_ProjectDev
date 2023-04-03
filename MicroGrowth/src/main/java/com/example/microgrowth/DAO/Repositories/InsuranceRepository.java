package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Inssurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InsuranceRepository extends JpaRepository<Inssurance,Integer> {
    @Query(value = "select u.email from inssurance a join user u on a.users_id_user=u.id_user" +
            " join transaction t on u.id_user=t.users_id_user where  " +
            "  t.date_transaction='2023-04-03' and t.rib_receiver='1'  and " +
            "t.categorie_transaction='INSURANCE' ", nativeQuery = true)
    List<String> selectEmailUser(Date d);

}
