package com.accountingapi.repository;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStoragePropertiesRepository extends JpaRepository<FileStorageProperties, Long> {

    List<FileStorageProperties> findAllById(List<Long> ids);
}
