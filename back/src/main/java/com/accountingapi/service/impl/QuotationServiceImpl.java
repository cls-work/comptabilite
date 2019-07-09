
package com.accountingapi.service.impl;

import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Product;
import com.accountingapi.model.Purchase;
import com.accountingapi.model.Quotation;
import com.accountingapi.repository.BillRepository;
import com.accountingapi.repository.ProductRepository;
import com.accountingapi.repository.QuotationRepository;
import com.accountingapi.security.repository.UserRepository;
import com.accountingapi.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private PurchaseServiceImpl purchaseServiceImpl;

    //Quotation save into database
    @Override
    public Quotation addQuotation(Quotation quotation) {
      /* List<Purchase> purchases=quotation.getPurchases();
        for(Purchase purchase:purchases)
        {
            quotation.setTotalHT(quotation.getTotalHT()+purchase.getAmountHT());
            quotation.setTotalTVA(quotation.getTotalTVA()+purchase.getAmountTVA());
            quotation.setTotalTTC(quotation.getTotalTTC()+purchase.getAmountTTC());
        }
        quotation.setTotalTTC(quotation.getTotalTTC()+quotation.getTaxStamp());
        */
        return (quotationRepository.save(quotation));

    }

    public Quotation updateQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }


    @Override
    public List<Quotation> findAllQuotations() {
        return quotationRepository.findAll();
    }


    @Override
    public Quotation findQuotationById(Long quotationId) {
        return quotationRepository.findById(quotationId).get();
    }


    //Delete non confirmed quotation by its id
    public void deleteQuotationById(Long quotationId) {
        quotationRepository.delete(this.findQuotationById(quotationId));
    }

    @Override
    public boolean existsById(Long quotationId) {
        return quotationRepository.existsById(quotationId);
    }

    //update quotation


}