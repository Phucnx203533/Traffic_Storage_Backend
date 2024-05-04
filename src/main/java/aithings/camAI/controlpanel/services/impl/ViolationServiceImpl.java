package aithings.camAI.controlpanel.services.impl;
import aithings.camAI.controlpanel.entity.ViolationInforEntity;
import aithings.camAI.controlpanel.exception.BEBusinessException;
import aithings.camAI.controlpanel.repository.ViolationRepository;
import aithings.camAI.controlpanel.services.MinioService;
import aithings.camAI.controlpanel.services.ViolationService;
import aithings.camAI.controlpanel.utils.DateTimeUtils;
import aithings.camAI.controlpanel.utils.constant.ETypeTraffic;
import aithings.camAI.controlpanel.utils.constant.MinioConstant;
import aithings.camAI.controlpanel.utils.response.ResCode;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.FileSystems;


@Service
public class ViolationServiceImpl implements ViolationService {
    private static final String ROOT = FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");
    private final ViolationRepository violationRepository;

    private final MinioService minioService;

    public ViolationServiceImpl(ViolationRepository violationRepository, MinioService minioService) {
        this.violationRepository = violationRepository;
        this.minioService = minioService;
    }

    @Override
    public void addViolation(MultipartFile imageViolation,
                             MultipartFile imageLicenseplateViolation,
                             MultipartFile videoVilation,
                             ViolationInforEntity violationInforEntity)throws  Exception{
        if(ETypeTraffic.findByNameVi(violationInforEntity.getTypeTraffic()) == null){
            throw new BEBusinessException(ResCode.VIOLATION_TYPE_TRAFFIC_NOT_FOUND);
        }
        String pathObject = DateTimeUtils.convertDateToStringYYYYMMDD(violationInforEntity.getTimeViolation(),violationInforEntity.getCameraIdDetectViolation());
        minioService.putObject(
                MinioConstant.BUCKET_IMAGE_VIOLATION,
                pathObject,
                imageViolation
                );
        minioService.putObject(
                MinioConstant.BUCKET_IMAGE_LICENSEPLATE_VIOLATION,
                pathObject,
                imageLicenseplateViolation
        );
        minioService.putObject(
                MinioConstant.BUCKET_VIDEO_VIOLATION,
                pathObject,
                videoVilation
        );
        violationInforEntity.setNameVideoViolation(videoVilation.getOriginalFilename());
        violationInforEntity.setNameImageViolation(imageViolation.getOriginalFilename());
        violationInforEntity.setNameImageLicenseplateViolation(imageLicenseplateViolation.getOriginalFilename());
        violationRepository.save(violationInforEntity);


    }

    @Override
    public InputStreamResource getImageViolation(String name,String bucketName) {
        ViolationInforEntity violationInforEntity = violationRepository.findByNameImageViolationOrNameImageLicenseplateViolationOrNameVideoViolation(name);
        String object = DateTimeUtils.convertDateToStringYYYYMMDD(violationInforEntity.getTimeViolation(),violationInforEntity.getCameraIdDetectViolation())
                +name;
        return minioService.getObject(bucketName,object);
    }

    @Override
    public String getPathVideoViolation(String nameVideo){
        return null;
//        return PATH_VIDEO_VIOLATION+"/"+nameVideo;
    }
    @Override
    public String getPathImageViolation(String nameImageViolation){
        return null;
//        return PATH_IMAGE_VIOLATION+"/"+nameImageViolation;

    }
    @Override
    public String getPathImageLicenseplateViolation(String nameImageLicenseplateViolation){
//        return PATH_IMAGE_LICENSEPLATE_VIOLATION+"/"+nameImageLicenseplateViolation;
        return null;
    }


}
