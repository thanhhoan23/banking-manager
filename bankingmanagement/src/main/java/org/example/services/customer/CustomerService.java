package org.example.services.customer;

import org.example.model.Customer;
import org.example.repository.CustomerRepository;
import org.example.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
    @Override
    public Boolean existsByIdEquals(long id) {
        return customerRepository.existsById(id);
    }
    @Override
    public List<Customer> findAllByIdNot(Long id) {
        return transferRepository.findAllByIdNot(id);
    }
}
