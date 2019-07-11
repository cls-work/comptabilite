
package com.accountingapi.service;

import com.accountingapi.model.CheckPayment;

import java.util.List;


public interface CheckService {

    public void addCheck(CheckPayment check);

    public List<CheckPayment> findAllChecks();

    public CheckPayment findCheckById(Long id);

    public void updateCheck(CheckPayment check) ;

    public void deleteCheck(CheckPayment check);

    public boolean existsById(Long id);
}


