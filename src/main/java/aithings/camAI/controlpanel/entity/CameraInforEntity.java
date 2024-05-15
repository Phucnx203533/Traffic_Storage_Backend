package aithings.camAI.controlpanel.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(collection = CAMERA_COLLECTION)
@Getter
@Setter
public class CameraInforEntity extends BaseEntity {

    @Id
    private String id;
    @Field(NAME)
    private String name;
    @Field(LINK_RTSP)
    private String linkRtsp;
    @Field(LINK_HTTP_IMAGE)
    private String linkHttpImage;
    @Field(LATITUDE)
    private Double lat;
    @Field(LONGITUDE)
    private Double lng;
    @Field(STATUS)
    private Integer status;
    @Field(ADDRESS)
    private String address;
    @Field(CITY)
    private String city;
    @Field(ADMIN_CENTER)
    private String adminCenter;
    @Field(LANE)
    private String lane;



}
