
package com.accountingapi.service;

import com.accountingapi.model.Check;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CheckService {

    public void addCheck(Check check);

    public List<Check> findAllChecks();

    public Check findCheckById(Long id);

    public void updateCheck(Check check) ;

    public void deleteCheck(Check check);

    public boolean existsById(Long id);
}


