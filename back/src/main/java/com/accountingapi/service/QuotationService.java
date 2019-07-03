package com.accountingapi.service;

import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Product;
import com.accountingapi.model.Quotation;
import com.accountingapi.repository.BillRepository;
import com.accountingapi.repository.ProductRepository;
import com.accountingapi.repository.QuotationRepository;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuotationService {
    @Autowired
    ProductService productService;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private HistoricalService historicalService;

    @Autowired
    private UserRepository userRepository;

    @Autowired

    private ProductRepository productRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    //Quotation save into database
    public Quotation saveQuotation(Quotation quotation){
        return(quotationRepository.save(quotation));

    }

    public List<Quotation> findAll(){
        List<Quotation> allquotations = new ArrayList<>();
        allquotations=quotationRepository.findAll();
        return allquotations;
    }

    public Quotation getQuotationById(Long id){
        return quotationRepository.findByQuotationId(id);
    }


    public List<FileStorageProperties>findFilesByBillId(Long id){
        return quotationRepository.findByQuotationId(id).getFileStorageProperties();
    }


    //Delete product by its id
    public void deleteProductById(String productId) {
        Product product = productService.getProductById(productId);
        List<Product> products=new ArrayList<>();
        products.add(product);
        List<Quotation> quotations=quotationRepository.findByProducts(products);
        //Bill bill = getBillById(product.getBill().getBillId());
        Quotation quotation = quotations.get(0);
        quotation.setTotalHT(quotation.getTotalHT()-product.getAmountHT());
        quotation.setTotalTVA(quotation.getTotalTVA()-product.getAmountTVA());
        quotation.setTotalTTC(quotation.getTotalTTC()-product.getAmountTTC());
        saveQuotation(quotation);
        productRepository.deleteByProductId(productId);
    }

    //Find product by quotation id
    public List<Product> getProductsByQuotationId(Long id){

        Quotation quotation=getQuotationById(id);

        return quotation.getProducts();
    }
}
