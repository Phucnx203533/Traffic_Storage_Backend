package aithings.camAI.controlpanel.controller;
import aithings.camAI.controlpanel.entity.ViolationInforEntity;
import aithings.camAI.controlpanel.services.ViolationService;
import aithings.camAI.controlpanel.utils.constant.*;
import aithings.camAI.controlpanel.utils.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@RequestMapping("/api/v1/violation")
@RestController
@Scope("request")
@Slf4j
public class ViolationController {

    private final ViolationService violationService;

    public ViolationController(ViolationService violationService) {
        this.violationService = violationService;
    }


    @PreAuthorize("hasAnyAuthority('UPLOAD_VIOLATION')")
    @RequestMapping(value = "/addViolation" ,consumes =MediaType.MULTIPART_FORM_DATA_VALUE ,method = RequestMethod.POST)
    public CommonResult addViolation(HttpServletRequest request,
                                     @RequestParam(value = "violation",required = false) String violation,
                                     @RequestParam(value = "licenseplate",required = false) String licenseplate,
                                     @RequestParam("time") String timeViolation,
                                     @RequestParam(value = "camera_id",required = false) String cameraId,
                                     @RequestParam(value = "type_traffic",required = false)String typeTraffic,
                                     @RequestPart("image_violation") MultipartFile imageViolation,
                                     @RequestPart("image_licenseplate_violation") MultipartFile imageLicenseplateViolation,
                                     @RequestPart("video_violation")MultipartFile videoViolation
    ){
        log.info("Add Violation");
        try {
            ViolationInforEntity violationInforEntity = new ViolationInforEntity(violation,licenseplate,new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss").parse(timeViolation),cameraId,typeTraffic);
            violationService.addViolation(imageViolation,imageLicenseplateViolation,videoViolation,violationInforEntity);
        } catch (Exception e) {
            log.error("Internal error while upload violation: {}", e.getMessage());
            return CommonResult.serverError();
        }
        return CommonResult.success();
    }



    @GetMapping(value = "/videoViolationSrc", produces = "video/mp4")
    @ResponseBody
    public InputStreamSource videoViolationSource(@RequestParam(value="id", required=true) String nameVideoViolation) {
        return violationService.getImageViolation(nameVideoViolation, MinioConstant.BUCKET_VIDEO_VIOLATION);
    }

    @GetMapping(value = "/imageViolationSrc", produces = "video/mp4")
    @ResponseBody
    public InputStreamSource imageViolationSource(@RequestParam(value="id", required=true) String imageViolation) {
        return violationService.getImageViolation(imageViolation,MinioConstant.BUCKET_IMAGE_VIOLATION);
    }

//    @GetMapping(value = "/imageViolationSrc", produces = "image/jpeg")
//    @ResponseBody
//    public FileSystemResource imageViolationSource(@RequestParam(value="id", required=true) String imageViolation) {
//        return new FileSystemResource(new File(violationService.getPathImageViolation(imageViolation)));
//    }

    @GetMapping(value = "/imageLicenseplateViolationSrc", produces = "image/png")
    @ResponseBody
    public InputStreamSource imageLicenseplateViolationSource(@RequestParam(value="id", required=true) String imageLicenseplateViolation) {
        return violationService.getImageViolation(imageLicenseplateViolation,MinioConstant.BUCKET_IMAGE_LICENSEPLATE_VIOLATION);
    }
//    @GetMapping(value = "/imageLicenseplateViolationSrc", produces = "image/png")
//    @ResponseBody
//    public FileSystemResource imageLicenseplateViolationSource(@RequestParam(value="id", required=true) String imageLicenseplateViolation) {
////        return new FileSystemResource(new File(violationService.getPathImageLicenseplateViolation(imageLicenseplateViolation)));
//        return  null;
//    }
}
