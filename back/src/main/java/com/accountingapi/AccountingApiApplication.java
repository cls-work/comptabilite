package com.accountingapi;

import com.accountingapi.model.Bill;
import com.accountingapi.service.BillService;
import com.accountingapi.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class AccountingApiApplication implements CommandLineRunner{

    @Autowired
    BillService billService;
    public static void main(String[] args)

    {
        SpringApplication.run(AccountingApiApplication.class, args);
    }

    @Override
    public void run(String... params) throws Exception {
       /* Bill bill = new Bill();
        //bill.setBillId("abc");
        bill.setCheckPayment(true);
        bill.setCheckReference(Double.valueOf(5647893));
        String str="2015-03-31";
        Date date= Date.valueOf(str);//converting string into sql date
        bill.setDate(date);
        bill.setDeleted(false);
        bill.setProvider("ddfg");
        bill.setTaxStamp((double)12);
        System.out.println(bill.toString());
        billService.addBill((long) 5,bill);*/
        System.out.println(LogicService.getCurrentTimeUsingCalendar());

    }
}
