package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
    @Query(value = "SELECT * FROM investment i WHERE YEAR(i.date_inv) =?1",nativeQuery = true)
    List<Investment> creditParAnnee(int annnee);

    @Query("SELECT Sum(amountInves) as seuil FROM Investment WHERE methodInvestissement = 'PONZI_PYRAMID'")
    int getsommeINV();
    @Query(value = "SELECT u FROM User  u join Investment i on i.users.idUser=u.idUser WHERE i.methodInvestissement= 'PONZI_PYRAMID' ")
    List<User> getNbrINPONZI();
    @Query(value = "select  I.amountInves from Investment I join User U on I.users.idUser=U.idUser where I.users.idUser=?1 and  I.methodInvestissement= 'PONZI_PYRAMID' ")
    double getAmountbyuserID(int idUser);
    @Query(value = "select i FROM Investment i   where i.users.idUser=?1")
    List<Investment> getlistByidUser(int idUser);



}
