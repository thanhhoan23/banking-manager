package com.cg.controller;

import com.cg.model.Customer;
import com.cg.model.dto.CustomerCreateDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/cp/customers")
public class CustomerCPController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private AppUtil appUtil;

    @GetMapping
    public String showCPListCustomerPage(@RequestParam(required = false) String searchKey, Model model) {
        List<Customer> customers;

        if (searchKey != null) {
            searchKey = "%" + searchKey + "%";

            customers = customerService.findAllByFullNameLikeOrEmailLikeOrPhoneLike(searchKey, searchKey, searchKey);
        }
        else {
            customers = customerService.findAll();
        }

        model.addAttribute("customers", customers);

        return "cp/customer/list";
    }

    @GetMapping("/create")
    public String showCPCreateCustomerPage(ModelMap modelMap) {
        modelMap.addAttribute("customerCreateDTO", new CustomerCreateDTO());
//        modelMap.addAttribute("errors", false);
        return "cp/customer/create";
    }

    @GetMapping("/edit/{id}")
    public String showCPEditCustomerPage(ModelMap modelMap, @PathVariable Long id) {

        Optional<Customer> customerOptional = customerService.findById(id);

        if (!customerOptional.isPresent()) {
            modelMap.addAttribute("error", "Customer ID not valid");
        }

        modelMap.addAttribute("customer", customerOptional.get());

        return "cp/customer/edit";
    }

    @PostMapping("/create")
    public String doCreateCustomer(ModelMap modelMap, @Validated @ModelAttribute CustomerCreateDTO customerCreateDTO, BindingResult bindingResult) {

        new CustomerCreateDTO().validate(customerCreateDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            modelMap.addAttribute("errors", true);
            return "cp/customer/create";
        }

        Customer customer = new Customer();
        customer.setId(null);
        customer.setFullName(customerCreateDTO.getFullName());
        customer.setEmail(customerCreateDTO.getEmail());
        customer.setPhone(customerCreateDTO.getPhone());
        customer.setAddress(customerCreateDTO.getAddress());
        customer.setBalance(BigDecimal.valueOf(Long.parseLong(customerCreateDTO.getBalance())));

        customerService.save(customer);
        modelMap.addAttribute("customer", new Customer());

        return "cp/customer/create";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteById(id);

        return "redirect:/cp/customers";
    }
}
