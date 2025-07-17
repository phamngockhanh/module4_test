package com.example.demo.dto;


import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionInfoDto implements Validator {
    private String code;
    private LocalDate date;
    private Double price;
    private Double square;

    private Customer customer;

    private Category category;
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        TransactionInfoDto transactionInfoDto = (TransactionInfoDto) target;
        if(transactionInfoDto.getCode().isEmpty()){
            errors.rejectValue("code", "Empty", "Không được để trống");
        }else if(!transactionInfoDto.getCode().matches("^MGD-[0-9]{4}$")){
            errors.rejectValue("code", "Not Match", "Vui lòng nhập khớp định dạng, MGD-xxxx");
        }

        if(transactionInfoDto.getCustomer()==null){
            errors.rejectValue("customer", "Empty", "Không được để trống");
        }

        if(transactionInfoDto.getCategory()==null){
            errors.rejectValue("category", "Empty", "Không được để trống");
        }

        if(transactionInfoDto.getDate()==null){
            errors.rejectValue("date", "Empty", "Không được để trống");
        }
        if(transactionInfoDto.getPrice()==null){
            errors.rejectValue("price", "Empty", "Không được để trống");
        }else if(transactionInfoDto.getPrice()<=500000){
            errors.rejectValue("price", "Not Match", "Nhập giá lớn hơn 500000");
        }

        if(transactionInfoDto.getSquare()==null){
            errors.rejectValue("square", "Empty", "Không được để trống");
        }else if(transactionInfoDto.getSquare()<=20){
            errors.rejectValue("square", "Not Match", "Nhập diện tích > 20");
        }

    }
}
