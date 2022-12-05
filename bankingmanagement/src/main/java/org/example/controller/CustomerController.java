package org.example.controller;

import org.example.model.Customer;
import org.example.model.Deposit;
import org.example.model.Transfer;
import org.example.model.Withdraw;
import org.example.model.dto.CustomerCreateDto;
import org.example.model.dto.CustomerEditDto;
import org.example.model.dto.DepositDto;
import org.example.services.customer.CustomerService;
import org.example.services.customer.ICustomerService;
import org.example.services.deposit.IDepositService;
import org.example.services.withdraw.IWithdrawService;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController  {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDepositService depositService;
    @Autowired
    private IWithdrawService withdrawService;

   @GetMapping
   public String showListCustomer (Model model) {
       List<Customer> customers;
       customers = customerService.findAll();
       model.addAttribute("customers",customers);
       return "/customers/index";
   }
    @GetMapping("/create")
    public String showCreatePage(Model model){
       model.addAttribute("customerCreateDto", new CustomerCreateDto());
//        model.addAttribute("customer", new Customer());
        return "/customers/create";
    }

    @PostMapping("/create")
    public String doCreateCustomer(Model model, @Validated @ModelAttribute CustomerCreateDto customerCreateDto, BindingResult bindingResult){
       new CustomerCreateDto().validate(customerCreateDto,bindingResult);
       if(bindingResult.hasFieldErrors()) {
           model.addAttribute("errors",true);
           model.addAttribute("customerCreateDto", customerCreateDto);
       } else {
           Customer customer = new Customer();
           customer.setId(null);
           customer.setFullName(customerCreateDto.getFullName());
           customer.setEmail(customerCreateDto.getEmail());
           customer.setPhone(customerCreateDto.getPhone());
           customer.setAddress(customerCreateDto.getAddress());
           customer.setBalance(new BigDecimal(0L));
           customer.setUpdateAt(null);
           customerService.save(customer);
           model.addAttribute("customerCreateDto", new CustomerCreateDto());
           model.addAttribute("message", true);
       }
        return "/customers/create";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm (Model model, @PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        model.addAttribute("customerEditDto", customerOptional.get());
        return "/customers/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit (Model model,@PathVariable Long id, @Validated @ModelAttribute CustomerEditDto customerEditDto, BindingResult bindingResult){
       Optional<Customer> customerOptional = customerService.findById(id);
        System.out.println(customerEditDto);
       new CustomerEditDto().validate(customerEditDto,bindingResult);
        if (customerOptional.isPresent()) {
            if(bindingResult.hasFieldErrors()) {
                model.addAttribute("errors",true);
                model.addAttribute("customerEditDto",customerEditDto);
            } else {
                Customer customer = customerOptional.get();
                customer.setFullName(customerEditDto.getFullName());
               customer.setEmail(customerEditDto.getEmail());
               customer.setPhone(customerEditDto.getPhone());
               customer.setAddress(customerEditDto.getAddress());
               customerService.save(customer);
//                model.addAttribute("customerEditDto",customerEditDto);
                model.addAttribute("customerEditDto", customer);
    //        List<Customer> customers = customerService.findAll();
    //        model.addAttribute("customers", customers);
               model.addAttribute("message", true);};
       } else {
           model.addAttribute("errors","Customer can not find");
       }

       return "/customers/edit";
    }

    @GetMapping("/suspended/{id}")
    public String showSuspendedForm (Model model, @PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        model.addAttribute("customer", customerOptional.get());
        return "/customers/suspended";
    }

    @PostMapping("/suspended/{id}")
    public String doSuspendedPage (Model model, @PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(customerOptional.isPresent()) {
            customerService.deleteById(id);
            List<Customer> customers = customerService.findAll();
            model.addAttribute("customers", customers);
        }else {
            model.addAttribute("errors","Customer can not find");
        }

        return "/customers/index";
    }

    @GetMapping("/deposit/{id}")
    public String showDepositPage (Model model, @PathVariable Long id, @ModelAttribute Deposit deposit){
        Optional<Customer> customerOptional = customerService.findById(id);
        model.addAttribute("customer", customerOptional.get());
        model.addAttribute("deposit",deposit);
        return "/customers/deposit";
    }
    @PostMapping("/deposit/{id}")
    public String doDeposit (Model model, @PathVariable Long id,@ModelAttribute Deposit deposit){
           Optional<Customer> customerOptional = customerService.findById(id);
           if(customerOptional.isPresent()) {
               Customer newCustomer = customerOptional.get();
               BigDecimal beforeBalance = newCustomer.getBalance();
               BigDecimal transactionAmount = deposit.getTransactionAmount();
               BigDecimal newBalance = beforeBalance.add(transactionAmount);
               deposit.setCustomer(newCustomer);
               newCustomer.setBalance(newBalance);
               depositService.save(deposit);
               customerService.save(newCustomer);
               model.addAttribute("customer", newCustomer);
               model.addAttribute("deposit", new Deposit());
               model.addAttribute("message", true);
           }else {
               model.addAttribute("errors","Customer can not find");
           }
        return "/customers/deposit";
    }
//    @PostMapping("/deposit/{id}")
//    public String doDeposit (Model model, @PathVariable Long id,@Validated @ModelAttribute DepositDto depositDto,BindingResult bindingResult){
//        Optional<Customer> customerOptional = customerService.findById(id);
//         Deposit deposit = new Deposit();
//        new DepositDto().validate(depositDto,bindingResult);
//        if(customerOptional.isPresent()) {
//            if (bindingResult.hasFieldErrors()){
//                model.addAttribute("errors", true);
//                model.addAttribute("depositDto",depositDto);
//            } else {
//                Customer newCustomer = customerOptional.get();
//                BigDecimal beforeBalance = newCustomer.getBalance();
//                String transactionAmount = depositDto.getTransactionAmount();
//                BigDecimal newBalance = beforeBalance.add(BigDecimal.valueOf(Long.parseLong(transactionAmount)));
//                depositDto.setCustomer(newCustomer);
//                newCustomer.setBalance(newBalance);
//                deposit.setTransactionAmount(BigDecimal.valueOf(Long.parseLong(depositDto.getTransactionAmount())));
//                depositService.save(deposit);
//                customerService.save(newCustomer);
//                model.addAttribute("customer", newCustomer);
//                model.addAttribute("deposit", new DepositDto());
//                model.addAttribute("message", true);
//            }
//        }else {
//            model.addAttribute("errors","Customer can not find");
//        }
//        return "/customers/deposit";
//    }
    @GetMapping("/withdraw/{id}")
    public String showWithdrawForm(Model model, @PathVariable Long id, @ModelAttribute Withdraw withdraw){
       Optional<Customer> customerOptional= customerService.findById(id);
        model.addAttribute("customer", customerOptional.get());
        model.addAttribute("withdraw",withdraw);
        return "/customers/withdraw";

    }
    @PostMapping("/withdraw/{id}")
    public String doWithdraw(Model model, @PathVariable Long id, @ModelAttribute Withdraw withdraw) {
        Optional<Customer> customerOptional= customerService.findById(id);
        Customer newCustomer = customerOptional.get();
        BigDecimal beforeBalance = newCustomer.getBalance();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        BigDecimal newBalance = beforeBalance.subtract(transactionAmount);
        withdraw.setCustomer(newCustomer);
        newCustomer.setBalance(newBalance);
        withdrawService.save(withdraw);
        customerService.save(newCustomer);
        model.addAttribute("customer", newCustomer);
        model.addAttribute("withdraw",new Withdraw());
        model.addAttribute("message", true);
        return "/customers/withdraw";
    }
    @GetMapping("/transfer/{id}")
    public String showFormTransfer (Model model, @PathVariable Long id, @ModelAttribute Transfer transfer) {
       Optional<Customer> optionalSender = customerService.findById(id);
       Customer sender = optionalSender.get();
       List<Customer> recipient = customerService.findAllByIdNot(id);
       model.addAttribute("transfer", transfer);
       model.addAttribute("sender",sender);
       model.addAttribute("recipient",recipient);
       return "/customers/transfer";
    }
    
}
