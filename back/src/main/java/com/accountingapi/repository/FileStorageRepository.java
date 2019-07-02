package com.accountingapi.repository;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileStorageRepository extends JpaRepository<FileStorageProperties, Long> {


}
