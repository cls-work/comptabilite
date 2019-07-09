package com.accountingapi.controller;


import com.accountingapi.model.Check;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.service.BillService;
        import com.accountingapi.service.CheckService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.web.bind.annotation.*;

        import javax.validation.Valid;
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
    public Check getCheckById(@PathVariable("id") String id) {
        return checkService.getCheckById(id);
    }

    public Check addCheck(@CurrentUser UserPrincipal currentUser, @Valid Check check) {


       Check newCheck = checkService.updateCheck(check);
        return (newCheck);

    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PutMapping("/{billId}")
    public Check editBill(@CurrentUser UserPrincipal currentUser, @PathVariable String checkId, @Valid Check check) {



            Check newCheck = checkService.updateCheck(check);
            return newCheck;

    }
}
