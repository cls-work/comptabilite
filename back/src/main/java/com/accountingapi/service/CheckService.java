package com.accountingapi.service;

import com.accountingapi.model.Check;
import com.accountingapi.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public interface CheckService {

    public List<Check> findAll();

    public Check getCheckById(String id);

    public void updateCheck(Check check) ;
public void deleteCheck (Check check);
}

