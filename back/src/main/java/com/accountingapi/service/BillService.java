
package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.model.Product;
import com.accountingapi.repository.BillRepository;
import com.accountingapi.repository.ProductRepository;
import com.accountingapi.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillService {


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

    //Bill save into database
    public Bill addBill(Bill bill){
        bill.setId(LogicService.getAlphaNumericString(5));
        return(billRepository.save(bill));

    }

    public List<Bill> findAll(){
        List<Bill> allbills = new ArrayList<>();
        allbills=billRepository.findAll();
        List<Bill> bills = new ArrayList<>();
        for(Bill bill:allbills){
            if(!bill.getDeleted()){
                bills.add(bill);
            }
        }

        return bills;
    }

    public Bill getBillById(String id){
        return billRepository.findByBillId(id);
    }

    //Bill update
    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    public void deleteBill(Bill bill){
        bill.setDeleted(true);
    }

    public List<FileStorageProperties>findFilesByBillId(String billId){
        return billRepository.findByBillId(billId).getFileStorageProperties();
    }


    //Delete product by its id
    public void deleteProductById(String productId) {
        Product product = productService.getProductById(productId);
        List<Product> products=new ArrayList<>();
        products.add(product);
        List<Bill> bills=billRepository.findByProducts(products);
        //Bill bill = getBillById(product.getBill().getBillId());
        Bill bill = bills.get(0);
        bill.setTotalHT(bill.getTotalHT()-product.getAmountHT());
        bill.setTotalTVA(bill.getTotalTVA()-product.getAmountTVA());
        bill.setTotalTTC(bill.getTotalTTC()-product.getAmountTTC());
        updateBill(bill);
        productRepository.deleteByProductId(productId);
    }

    //Find product by bill id
    public List<Product> getProductsByBillId(String billId){
        Bill bill = new Bill();
        bill=getBillById(billId);

        return bill.getProducts();
    }

}
