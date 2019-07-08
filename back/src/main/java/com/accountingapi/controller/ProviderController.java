package com.accountingapi.controller;

import com.accountingapi.model.Provider;
import com.accountingapi.service.impl.ProductServiceImpl;
import com.accountingapi.service.impl.ProviderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    ProviderServiceImpl providerService;

    @PostMapping
    public void addProvider(@Valid @RequestBody Provider provider) {
        providerService.addProvider(provider);
    }

    @GetMapping("/{providerId}")
    public Provider findProviderById(@PathVariable("providerId") Long providerId) {
        return providerService.getProviderById(providerId);
    }

    @GetMapping
    public List<Provider> findAllProviders() {
        return providerService.findAllProviders();
    }

    @DeleteMapping("/{providerId}")
    public void deleteProviderById(@PathVariable("providerId") Long providrId) {
        providerService.deleteProvider(providrId);
    }

    @PutMapping
    public Provider updateProvider(@Valid @RequestBody Provider provider) {
        return providerService.updateProvider(provider);
    }
}
