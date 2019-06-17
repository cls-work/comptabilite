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

        System.out.println("________________________________________________");
        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+bill.toString());
        System.out.println("________________________________________________");
        for (Product product:products) {
            product.setBill(bill);
            product.setProductId(IdService.getAlphaNumericString(20));
            System.out.println("________________________________________________");
            System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+product.toString());
            System.out.println("________________________________________________");
            productRepository.save(product);
        }

    }


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
