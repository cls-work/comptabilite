        package com.accountingapi.controller;


        import com.accountingapi.model.Check;
        import com.accountingapi.service.BillService;
        import com.accountingapi.service.CheckService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/checks")
public class CheckController {

    @Autowired
    CheckService checkService;

    @Autowired
    BillService billService;

    @PreAuthorize("")
    @GetMapping("")
    public List<Check> displayAllChecks() {
        return checkService.findAll();
    }

    @PreAuthorize("")
    @GetMapping("/{id}")
    public Check getBillById(@PathVariable("id") String id) {
        return checkService.getBillById(id);
    }




}
