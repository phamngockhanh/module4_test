package com.example.demo.service;

import com.example.demo.entity.TransactionInfo;
import com.example.demo.repository.ITransactionInfoRepo;
import org.hibernate.cfg.Unsafe;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TransactionInfoService implements ITransactionInfoService{
    private final ITransactionInfoRepo transactionInfoRepo;

    public TransactionInfoService(ITransactionInfoRepo transactionInfoRepo) {
        this.transactionInfoRepo = transactionInfoRepo;
    }

    @Override
    public List<TransactionInfo> findAll(Long categoryId, String customerName) {
        return transactionInfoRepo.findAll(categoryId,customerName);
    }

    @Override
    public void deleteById(Long id) {
        transactionInfoRepo.deleteById(id);
    }

    @Override
    public void save(TransactionInfo transactionInfo) {
        transactionInfoRepo.save(transactionInfo);
    }

    @Override
    public void add(TransactionInfo transactionInfo) {
        transactionInfoRepo.save(transactionInfo);
    }

    @Override
    public TransactionInfo findById(Long id) {
        return transactionInfoRepo.findById(id).orElse(null);
    }
}
