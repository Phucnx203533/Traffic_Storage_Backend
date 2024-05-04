package aithings.camAI.controlpanel.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

import java.util.Date;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(collection = VIOLATION_COLLECTION)
@Getter
@Setter
public class ViolationInforEntity extends BaseEntity{

    @Id
    private String id;
    @Field(VIOLATION)
    private String violation;
    @Field(LICENSEPLATE)
    private String licenseplate;
    @Field(TIME_VIOLATION)
    private Date timeViolation;
    @Field(CAMERA_DETECT_VIOLATION)
    private String cameraIdDetectViolation;
    @Field(NAME_IMAGE_VIOLATION)
    private String nameImageViolation;
    @Field(NAME_IMAGE_LICENSEPLATE_VIOLATION)
    private String nameImageLicenseplateViolation;
    @Field(NAME_VIDEO_VIOLATION)
    private String nameVideoViolation;
    @Field(TYPE_TRAFFIC)
    private String typeTraffic;

    public ViolationInforEntity() {
    }


    public ViolationInforEntity(String violation, String licenseplate, Date timeViolation, String cameraIdDetectViolation, String typeTraffic) {
        this.violation = violation;
        this.licenseplate = licenseplate;
        this.timeViolation = timeViolation;
        this.cameraIdDetectViolation = cameraIdDetectViolation;
        this.typeTraffic= typeTraffic;
    }
}
