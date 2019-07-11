
package com.accountingapi.controller;


import com.accountingapi.model.Check;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
        import com.accountingapi.service.CheckService;
import com.accountingapi.service.impl.CheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/checks")
public class CheckController {

    @Autowired
    CheckServiceImpl checkService;


    @GetMapping
    public List<Check> displayAllChecks() {
        return checkService.findAllChecks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Check> findCheckById(@PathVariable("id") Long id) {
        if (checkService.existsById(id))
            return new ResponseEntity<Check>(checkService.findCheckById(id), HttpStatus.OK);
        else return new ResponseEntity("Check not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Check> addCheck(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody Check check) {
        checkService.addCheck(check);
        return new ResponseEntity<Check>(check, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<?> editCheck(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody Check check) {

        if (checkService.existsById(check.getId())) {
            checkService.updateCheck(check);
            return new ResponseEntity<>(check, HttpStatus.OK);
        }
        return new ResponseEntity("Check with id" + check.getId() + " not found",
                HttpStatus.NOT_FOUND);

    }
}

