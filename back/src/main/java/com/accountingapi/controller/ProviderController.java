package com.accountingapi.controller;

import com.accountingapi.model.Provider;
import com.accountingapi.service.impl.ProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    ProviderServiceImpl providerService;

    // -------------------Create a Provider---------------------------------------------
    @PostMapping
    public ResponseEntity<?> addProvider(@Valid @RequestBody Provider provider) {
        providerService.addProvider(provider);
        return new ResponseEntity<>(provider, HttpStatus.CREATED);
    }

    // -------------------Retrieve One Provider By ID---------------------------------------------
    @GetMapping("/{providerId}")
    public ResponseEntity<Provider> findProviderById(@PathVariable("providerId") Long providerId) {
        if (providerService.existsById(providerId))
            return new ResponseEntity<Provider>(providerService.findProviderById(providerId), HttpStatus.OK);
        else return new ResponseEntity("Provider not found", HttpStatus.NOT_FOUND);
    }


    // -------------------Retrieve All Providers---------------------------------------------
    @GetMapping
    public ResponseEntity<List<Provider>> findAllProviders() {
        List<Provider> providers = providerService.findAllProviders();
        if (providers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(providers, HttpStatus.OK);
    }

    // -------------------Delete a Provider---------------------------------------------
    @DeleteMapping("/{providerId}")
    public ResponseEntity<?> deleteProviderById(@PathVariable("providerId") Long providrId) {
        if (providerService.existsById(providrId)) {
            providerService.deleteProvider(providrId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Provider with id " + providrId + " not found", HttpStatus.NOT_FOUND);
    }

    // -------------------Update a Provider---------------------------------------------
    @PutMapping
    public ResponseEntity<?> updateProvider(@Valid @RequestBody Provider provider) {
        if (providerService.existsById(provider.getId())) {
            providerService.updateProvider(provider);
            return new ResponseEntity<>(provider, HttpStatus.OK);
        }
        return new ResponseEntity("Provider with id " + provider.getId() + " not found",
                HttpStatus.NOT_FOUND);
    }
}
