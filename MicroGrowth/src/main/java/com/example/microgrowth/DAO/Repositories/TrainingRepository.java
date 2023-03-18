package com.example.microgrowth.DAO.Repositories;


import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.time.LocalDate;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
    @Query("select t from Training t where t.finishdate<current_date ")
    List<Training> selectByDate();
    @Query("select t from Training t where t.finishdate=current_date-1 ")
    List<Training> selectByDate1();
    @Query(value = "select u.* from User u join  Training_user_list tl on tl.user_list_id_user=u.id_user join training t on t.id_training=tl.training_list_id_training where t.finishdate-1=current_date", nativeQuery = true)
    List<User>  selectUser();
    @Query(value = "select u from User u ")
    List<User>  selectUsers();
   Training findById(int id);
}
