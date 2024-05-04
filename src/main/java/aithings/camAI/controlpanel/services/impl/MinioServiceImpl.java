package aithings.camAI.controlpanel.services.impl;

import aithings.camAI.controlpanel.services.MinioService;
import aithings.camAI.controlpanel.utils.DateTimeUtils;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class MinioServiceImpl implements MinioService {



    @Autowired
    private MinioClient minioClient;

    @Override
    public void putObject(String bucketName, String path, MultipartFile file)throws Exception {
        InputStream fileContent = file.getInputStream();
        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(path+file.getOriginalFilename()).stream(fileContent, file.getSize(), -1).build());
        fileContent.close();
        return;
    }

    @Override
    public InputStreamResource getObject(String bucketName, String object) {
        try {
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(object).build());
            inputStream.close();
            return new InputStreamResource(inputStream);
        }catch (Exception e){
            return null;
        }

    }

    @Override
    public void deleteObject(String bucketName, String object) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(object).build());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
