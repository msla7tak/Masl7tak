package com.application.masl7tak.rest.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.application.masl7tak.service.serviceImp.AmazonS3Service;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class AmazonS3Controller {


    private final AmazonS3Service amazonS3Service;

    public AmazonS3Controller(AmazonS3Service amazonS3Service) {
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("user/s3/upload")
    public ResponseEntity<String> uploadFilesToS3(@RequestParam(value = "file") MultipartFile[] files) {
        try {
            String bucketName = "msla7takproject";
            List<File> fileList = new ArrayList<>();
            String nameList = "";

            for (MultipartFile file : files) {
                File convertedFile = convertMultiPartFileToFile(file);
                fileList.add(convertedFile);
                nameList=nameList + convertedFile.getName()+",";
            }

            nameList = nameList.substring(0, nameList.length() - 1);

            amazonS3Service.uploadFilesToS3(bucketName, fileList);
            return ResponseEntity.ok(nameList);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public String uploadFiles(MultipartFile[] files) {
        try {
            String bucketName = "msla7takproject";
            List<File> fileList = new ArrayList<>();
            String nameList ="";
            for (MultipartFile file : files) {
                File convertedFile = convertMultiPartFileToFile(file);
                fileList.add(convertedFile);
                nameList=nameList + convertedFile.getName()+",";
            }
             nameList = nameList.substring(0, nameList.length() - 1);
         amazonS3Service.uploadFilesToS3(bucketName, fileList);
            return  nameList;
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    private File convertMultiPartFileToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("public/s3/download")
    public ResponseEntity<InputStreamResource> downloadFileFromS3(@RequestParam("keyName") String keyName) {
        String bucketName = "msla7takproject";
        S3Object s3object = amazonS3Service.downloadFile(bucketName, keyName);

        InputStreamResource inputStreamResource = new InputStreamResource(s3object.getObjectContent());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(s3object.getObjectMetadata().getContentType()));
        headers.setContentLength(s3object.getObjectMetadata().getContentLength());
        headers.setContentDispositionFormData("attachment", keyName);

        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }


}