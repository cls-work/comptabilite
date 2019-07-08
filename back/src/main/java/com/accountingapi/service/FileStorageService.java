package com.accountingapi.service;

import com.accountingapi.model.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {

    String storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    List<FileStorageProperties> findAllById(List<Long> ids);

}
