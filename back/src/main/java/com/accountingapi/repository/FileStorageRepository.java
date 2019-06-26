package com.accountingapi.repository;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileStorageRepository extends JpaRepository<FileStorageProperties, Long> {

    List<FileStorageProperties> findAllByBill_BillId(String BillId);

}
