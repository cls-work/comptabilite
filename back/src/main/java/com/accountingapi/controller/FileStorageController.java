package com.accountingapi.controller;

import com.accountingapi.model.FileStorageProperties;
import com.accountingapi.repository.FileStorageRepository;
import com.accountingapi.security.JWT.CurrentUser;
import com.accountingapi.security.JWT.UserPrincipal;
import com.accountingapi.security.payload.ApiResponse;
import com.accountingapi.service.BillService;
import com.accountingapi.service.FileStorageService;
import com.accountingapi.service.HistoricalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileStorageController {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileStorageRepository fileStorageRepository;

    @Autowired
    private BillService billService;

    @Autowired
    private HistoricalService historicalService;


    @PostMapping("/uploadFile")
    public FileStorageProperties uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        FileStorageProperties fileStorageProperties = new FileStorageProperties(fileName,
                file.getContentType(), file.getSize());
        fileStorageRepository.save(fileStorageProperties);

        return fileStorageProperties;
    }

    @PostMapping("/uploadMultipleFiles")
    public List<FileStorageProperties> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/getAllFiles/{billId}")
    public List<FileStorageProperties> getAllFilesByBillId(@PathVariable  String billId){
        return billService.findFilesByBillId(billId);
    }

   @DeleteMapping("/deleteFile/{fileId}")
    public ResponseEntity<?> deleteFileById(@CurrentUser UserPrincipal currentUser, @PathVariable Long fileId){
        FileStorageProperties fileStorageProperties= fileStorageRepository.getOne(fileId);
        if(fileStorageProperties!=null) {
            fileStorageRepository.delete(fileStorageProperties);
            /*String [] fileName=fileStorageProperties.getUploadDir().split("\\.");
            String mesage = "deleted file '"+fileName[0];*/
            return new ResponseEntity(new ApiResponse(true, "File deleted successfully "),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(new ApiResponse(false, "File does not exist"),
                HttpStatus.BAD_REQUEST);
    }
}