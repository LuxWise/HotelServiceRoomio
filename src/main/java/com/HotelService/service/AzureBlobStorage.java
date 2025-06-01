package com.HotelService.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AzureBlobStorage {
    @Value("${azure.storage.connection_string}")
    private String connectionString;

    @Value("${azure.storage.container_name}")
    private String containerName;

    private BlobContainerClient containerClient;

    @PostConstruct
    public void init() {
        containerClient =  new BlobContainerClientBuilder()
            .connectionString(connectionString)
            .containerName(containerName)
            .buildClient();
    }

    public String updateFile(MultipartFile file, UUID id) throws IOException {
        String filename = id.toString() + "-" + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(filename);

        blobClient.upload(file.getInputStream(), file.getSize());

        return blobClient.getBlobUrl();
    }

    public List<String> updateMultipartFiles(List<MultipartFile> files, UUID id) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String url = updateFile(file, id);
            uploadedUrls.add(url);
        }

        return uploadedUrls;
    }
}
