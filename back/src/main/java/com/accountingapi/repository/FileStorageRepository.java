package com.accountingapi.repository;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.data.repository.CrudRepository;

public interface FileStorageRepository extends CrudRepository<FileStorageProperties, Long> {
}
