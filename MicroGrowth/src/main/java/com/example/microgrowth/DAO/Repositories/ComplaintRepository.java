package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.RetourComplaint;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {

    @Query("SELECT COUNT(DISTINCT c.users.idUser) FROM Complaint c")
    Long countUniqueUsersWithComplaints();


    @Query( "select c from Complaint c where c.state=true")
    List<Complaint> selectByState1();
    @Query(value = "select u from User u ")
    List<User>  selectUsers();
    Complaint findByIdComplaint(int id);
    @Query( "select count(*)from Complaint c where c.retourComplaint=?1")
    double calculsatisfait(RetourComplaint r);
    @Query( "select count(*)from Complaint ")
    double totale();
    @Query( "select u from Complaint u where u.users.email=?1 ")
    List<Complaint> selectwithcurrentuser(String email);
}
