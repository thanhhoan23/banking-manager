package org.example.repository;

import org.example.model.Customer;
import org.example.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer,Long> {
    @Query(value = "select cus from Customer cus where cus.id <> ?1")
    public List<Customer> findAllByIdNot(Long id);
}
