package aithings.camAI.controlpanel.services;


import aithings.camAI.controlpanel.entity.ViolationInforEntity;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
public interface ViolationService {


    void addViolation(MultipartFile imageViolation, MultipartFile imageLicenseplateViolation, MultipartFile videoVilation, ViolationInforEntity violationInforEntity)throws  Exception;


    String getPathVideoViolation(String nameVideo);

    InputStreamResource getImageViolation(String nameImageViolation,String bucketName );

    String getPathImageViolation(String nameImageViolation);
    String getPathImageLicenseplateViolation(String nameImageLicenseplateViolation);


}
