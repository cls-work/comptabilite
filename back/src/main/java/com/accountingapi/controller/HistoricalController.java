
package com.accountingapi.controller;


import com.accountingapi.model.Bill;
import com.accountingapi.model.Historical;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.controller.UserController;
import com.accountingapi.security.model.User;
import com.accountingapi.security.service.UserService;
import com.accountingapi.security.service.impl.UserServiceImpl;
import com.accountingapi.service.HistoricalService;
import com.accountingapi.service.impl.HistoricalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/historicals")
public class HistoricalController {


    @Autowired
    private HistoricalServiceImpl historicalService;

    @Autowired
    private UserServiceImpl userService;

    // -------------------Retrieve All Historicals---------------------------------------------
    @GetMapping
    public ResponseEntity<?> findAllHistoricals() {
        List<Historical> historicals = historicalService.findAllHistoricals();
        if (historicals.isEmpty()) {
            return new ResponseEntity("No Historicals found", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(historicals, HttpStatus.OK);
    }

    // -------------------Retrieve Historical By ID---------------------------------------------

    @GetMapping("/{historicalId}")
    public ResponseEntity<Historical> findHistoricalById(@PathVariable("historicalId") Long historicalId) {
        if (historicalService.existsById(historicalId))
            return new ResponseEntity<Historical>(historicalService.findHistoricalById(historicalId), HttpStatus.OK);
        else return new ResponseEntity("Historical not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Retrieve All Historical By USER ID---------------------------------------------
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findHistoricalByUserId(@PathVariable("userId") Long userId) {
        if (userService.existsById(userId)) {
            User user = userService.findUserById(userId);
            List<Historical> historicals = historicalService.findAllHistoricalsByUser(user);
            if (historicals.isEmpty()) {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } else return new ResponseEntity<List<Historical>>(historicals, HttpStatus.OK);
        } else return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Create a Historical---------------------------------------------
    @PostMapping
    public ResponseEntity<Historical> addHistorical(@CurrentUser UserPrincipal currentUser, @RequestBody Historical historical) {
        historicalService.addHistorical(historical);
        return new ResponseEntity<Historical>(historical, HttpStatus.CREATED);

    }

}

