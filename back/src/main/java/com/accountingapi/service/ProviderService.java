package com.accountingapi.service;

import com.accountingapi.model.Provider;

import java.util.List;

public interface ProviderService {
    Provider addProvider(Provider provider);

    List<Provider> findAllProviders();

    Provider getProviderById(Long id);

    Provider updateProvider(Provider provider);

    void deleteProvider(Long providerId);
}
