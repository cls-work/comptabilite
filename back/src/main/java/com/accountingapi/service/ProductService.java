package com.accountingapi.service;

import com.accountingapi.model.Bill;
import com.accountingapi.model.Product;
import com.accountingapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BillService billService;

    //Add Product
    public void addProduct(String billId,List<Product> products){

        Bill bill=billService.getBillById(billId);

        for (Product product:products) {
            product.setProductId(LogicService.getAlphaNumericString(20));
            bill.setTotalHT(bill.getTotalHT()+product.getAmountHT());
            bill.setTotalTVA(bill.getTotalTVA()+product.getAmountTVA());
            bill.setTotalTTC(bill.getTotalTTC()+product.getAmountTTC());
            billService.updateBill(bill);
            productRepository.save(product);
        }

    }


    //Update Product
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    //Find Product by its id
    public Product getProductById(String id){
        return productRepository.findByProductId(id);
    }





}
