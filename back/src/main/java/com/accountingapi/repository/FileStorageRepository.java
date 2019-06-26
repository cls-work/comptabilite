package com.accountingapi.repository;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FileStorageRepository extends CrudRepository<FileStorageProperties, Long> {

    List<FileStorageProperties> findAllByBill_BillId(String BillId);
}
