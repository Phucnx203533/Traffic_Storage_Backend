package aithings.camAI.controlpanel.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public interface MinioService {


    void putObject(String bucketName, String path, MultipartFile file)throws Exception;

    InputStreamResource getObject(String bucketName,String object);

    void deleteObject(String bucketName,String object);
}
