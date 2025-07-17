package com.example.demo.controller;

import com.example.demo.dto.TransactionInfoDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Customer;
import com.example.demo.entity.TransactionInfo;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.ITransactionInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/transaction-info")
public class TransactionInfoController {

    private final ITransactionInfoService transactionInfoService;
    private final ICategoryService categoryService;
    private final ICustomerService customerService;


    public TransactionInfoController(ITransactionInfoService transactionInfoService, ICategoryService categoryService, ICustomerService customerService) {
        this.transactionInfoService = transactionInfoService;
        this.categoryService = categoryService;
        this.customerService = customerService;
    }

    @ModelAttribute("categoryList")
    List<Category> categoryList(){
        return categoryService.findAll();
    }
    @ModelAttribute("customerList")
    List<Customer> customerList(){
        return customerService.findAll();
    }


    @GetMapping("")
    public String findAll(
            @RequestParam(defaultValue = "") Long categoryId,
            @RequestParam(defaultValue = "") String customerName,
            Model model){
        List<TransactionInfo> transactionInfos = transactionInfoService.findAll(categoryId,customerName);
        model.addAttribute("transactionInfos",transactionInfos);
        model.addAttribute("categoryId",categoryId);
        model.addAttribute("customerName",customerName);
        return "/list";
    }

    @PostMapping("/delete")
    public String deleteTransactionInfo(@RequestParam("id") Long id){
        transactionInfoService.deleteById(id);
        return "redirect:/transaction-info";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("transactionInfoDto", new TransactionInfoDto());
        return "create";
    }

    @PostMapping("/create")
    public String createTransaction(@Validated @ModelAttribute("transactionInfoDto") TransactionInfoDto transactionInfoDto, BindingResult bindingResult, Model model){
        TransactionInfo transactionInfo = new TransactionInfo();
        new TransactionInfoDto().validate(transactionInfoDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "create";
        }
        BeanUtils.copyProperties(transactionInfoDto, transactionInfo);
        transactionInfoService.add(transactionInfo);
        return "redirect:/transaction-info";
    }
    @GetMapping("/{id}/detail")
    public String detail(@PathVariable("id") Long id,  Model model){
        TransactionInfo transactionInfo  = transactionInfoService.findById(id);
        model.addAttribute("transactionInfo", transactionInfo);
        return "detail";
    }

}
