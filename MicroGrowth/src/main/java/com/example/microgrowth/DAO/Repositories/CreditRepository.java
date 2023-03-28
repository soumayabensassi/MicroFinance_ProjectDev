package com.example.microgrowth.DAO.Repositories;


import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Objects;


public interface CreditRepository extends JpaRepository<Credit, Integer> {
    List<Credit> findByUsersEmail(String email);


    @Query(value = "select c.* from credit c where c.rembourse=?1",nativeQuery = true)
    List<Credit> creditRembouse(boolean b);
    @Query(value = "SELECT * FROM credit c WHERE YEAR(c.obtaining_date) =?1",nativeQuery = true)
    List<Credit> creditParAnnee(int annnee);
    @Query(value = "select SUM(t.amount_transaction) from credit c " +
            "join user u on c.users_id_user=u.id_user join transaction " +
            "t on u.id_user=t.users_id_user where t.rib_receiver=\"999999999\" " +
            "and YEAR(t.date_transaction)=?2 and t.type_transaction=\"BANK_TRANSFER\"" +
            "AND u.id_user=?1",nativeQuery = true)
    double SommeDepot(int id,int annee);
    @Query(value = "select t.id_transaction from credit c join user u on c.users_id_user=u.id_user join transaction t " +
            "on u.id_user=t.users_id_user where t.rib_receiver=\"999999999\"" +
            " and YEAR(t.date_transaction)=?2 and t.type_transaction=\"BANK_TRANSFER\"" +
            " AND u.id_user=?1",nativeQuery = true)
    List<Integer> listTransactiondelannee(int id, int annee);

    @Query(value = "select c.* from credit c join user u on c.users_id_user=u.id_user" +
            " join transaction t on u.id_user=t.users_id_user join " +
            "transaction_bank_account_list tb on u.id_user=tb.transaction_list_id_transaction" +
            " and c.date_echeance=t.date_transaction and t.rib_receiver='1' and " +
            "t.amount_transaction=c.monthly_amount ", nativeQuery = true)
    List<Credit> selectCreditRembourseeParMois();
    @Query(value ="select c.users_id_user from credit c where c.id_credit=?1" , nativeQuery = true)
    int SelectUserFromCredit(int i);


}
