package com.accountingapi.service.impl;

import com.accountingapi.model.Provider;
import com.accountingapi.repository.ProviderRepository;
import com.accountingapi.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Provider addProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public List<Provider> findAllProviders() {
        return providerRepository.findAll();
    }

    @Override
    public Provider getProviderById(Long id) {
        return providerRepository.findById(id).get();
    }

    @Override
    public Provider updateProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public void deleteProvider(Long providerId) {
        Provider provider = getProviderById(providerId);
        providerRepository.delete(provider);
    }
}
