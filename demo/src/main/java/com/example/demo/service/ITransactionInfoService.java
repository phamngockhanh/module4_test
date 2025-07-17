package com.example.demo.service;

import com.example.demo.dto.TransactionInfoDto;
import com.example.demo.entity.TransactionInfo;

import java.util.List;

public interface ITransactionInfoService {
    List<TransactionInfo> findAll(Long categoryId, String customerName);
    void deleteById(Long id);
    void save(TransactionInfo transactionInfo);
    void add(TransactionInfo transactionInfo);
    TransactionInfo findById(Long id);
}
