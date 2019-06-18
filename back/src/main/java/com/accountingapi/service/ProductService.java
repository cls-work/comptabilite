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
            product.setBill(bill);
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


    //Find product by bill id
    public List<Product> getProductsByBillId(String billId){
        Bill bill = new Bill();
        bill=billService.getBillById(billId);

        return productRepository.findProductsByBill(bill);
    }

    //Delete product by its id
    public void deleteProductById(String productId) {
        Product product = getProductById(productId);
        Bill bill = billService.getBillById(product.getBill().getBillId());
        bill.setTotalHT(bill.getTotalHT()-product.getAmountHT());
        bill.setTotalTVA(bill.getTotalTVA()-product.getAmountTVA());
        bill.setTotalTTC(bill.getTotalTTC()-product.getAmountTTC());
        billService.updateBill(bill);
        productRepository.deleteByProductId(productId);
    }

}
