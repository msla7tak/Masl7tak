package com.application.masl7tak.service.serviceImp;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AmazonS3Service {

    private final AmazonS3 s3Client;

    public AmazonS3Service(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    public List<String> uploadFilesToS3(String bucketName, List<File> files) {
        List<String> fileUrls = new ArrayList<>();
        for (File file : files) {
            String keyName = file.getName();
            s3Client.putObject(new PutObjectRequest(bucketName, keyName, file));
            String fileUrl = String.format("https://%s.s3.amazonaws.com/%s", bucketName, keyName);
            fileUrls.add(fileUrl);
        }
        return fileUrls;
    }


    public S3Object downloadFile(String bucketName, String keyName) {
        return s3Client.getObject(bucketName, keyName);
    }
}

