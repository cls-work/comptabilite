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

    public void addProduct(String billId,List<Product> products){

        Bill bill=billService.getBillById(billId);
        System.out.println(bill.getBillId());
        for (Product product:products) {
            product.setBill(bill);
            System.out.println("_____________________________________________________________");
            System.out.println(product);
            product.setProductId(IdService.getAlphaNumericString(20));
            productRepository.savee(product);
            System.out.println("_____________________________________________________________");
        }

       /* Bill bill=billService.getBillById(billId);
        Product product = new Product("123456", "skander", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),Long.valueOf(0), Long.valueOf(0), Long.valueOf(0),true, bill);
        productRepository.save(product);*/
    }

    // 7amza bch yaamel trigger fel bdd ki tajouti produit yekhou les produits mtaa l bill illi zednelou des produits w y3awed ye7seb totalttc totalht total tva

        /*List<Product> newProducts=productRepository.findProductsByBill(bill);
        double

        for (Product product:products){

        }*/

    public Product getProductById(String id){
        return productRepository.findByProductId(id);
    }


    public List<Product> getProductsByBillId(String billId){
        Bill bill = new Bill();
        bill=billService.getBillById(billId);

        return productRepository.findProductsByBill(bill);
    }

    public boolean deleteProduct(String productId) {
        return productRepository.deleteByProductId(productId);
    }

   /* public List<Product> getProductsByBillId(String id){
        return productRepository.findProductsByBill_Bill_id(id);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }*/
}
