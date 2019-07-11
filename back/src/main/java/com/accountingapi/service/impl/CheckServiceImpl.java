
package com.accountingapi.service.impl;

import com.accountingapi.model.CheckPayment;
import com.accountingapi.repository.CheckRepository;
import com.accountingapi.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {

    @Autowired
    CheckRepository checkRepository;


    @Override
    public void addCheck(CheckPayment check) {
        checkRepository.save(check);
    }

    @Override
    public List<CheckPayment> findAllChecks() {
        return checkRepository.findAll();
    }

    @Override
    public CheckPayment findCheckById(Long id) {
        return checkRepository.findById(id).get();
    }

    public void updateCheck(CheckPayment check) {

        checkRepository.save(check);
    }

    @Override
    public void deleteCheck(CheckPayment check) {
        checkRepository.delete(check);

    }

    @Override
    public boolean existsById(Long id) {
        return checkRepository.existsById(id);
    }

}

