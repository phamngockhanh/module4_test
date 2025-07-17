package com.example.demo.repository;

import com.example.demo.entity.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ITransactionInfoRepo extends JpaRepository<TransactionInfo,Long> {
    @Query(value = " select g.* from giao_dich g " +
            "join khach_hang kh on g.customer_id = kh.id " +
            "join loai_bat_dong_san lbds on g.category_id = lbds.id " +
            "where (:categoryId is null or lbds.id = :categoryId ) and kh.name like concat('%',:customerName,'%')",nativeQuery = true)
    List<TransactionInfo> findAll(@Param("categoryId") Long categoryId, @Param("customerName") String customerName);

    void removeById(Long id);

}
