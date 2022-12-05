package org.example.services.customer;

import org.example.model.Customer;
import org.example.services.IGeneralService;

import java.util.List;

public interface ICustomerService extends IGeneralService<Customer> {
    List<Customer> findAllByIdNot(Long id);
}
