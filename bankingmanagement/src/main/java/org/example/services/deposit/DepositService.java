package org.example.services.deposit;

import org.example.model.Deposit;
import org.example.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DepositService implements IDepositService{
    @Autowired
    private DepositRepository depositRepository;
    @Override
    public List<Deposit> findAll() {
        return null;
    }

    @Override
    public Optional<Deposit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Deposit getById(Long id) {
        return null;
    }

    @Override
    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @Override
    public void delete(Deposit deposit) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Boolean existsByIdEquals(long id) {
        return null;
    }
}
