package com.accountingapi.service;

import com.accountingapi.model.Check;
import com.accountingapi.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class CheckService {
    @Autowired
     CheckRepository checkRepository;


    public List<Check> findAll()
    {
        return checkRepository.findAll();

    }

    public Check getCheckById(String id) {

            return checkRepository.findByCheckId(id);
        }


    public void updateCheck(Check check) {

        checkRepository.save(check);
    }
public void deleteCheck (Check check)
{
    checkRepository.delete(check);
}
}

