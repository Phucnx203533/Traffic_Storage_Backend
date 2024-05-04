package aithings.camAI.controlpanel.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(DEVICE_PROCESS)

public class DeviceProcessEntity extends BaseEntity{

    @Id
    @Field("_id")
    private String serial;

    private String username;

    private String password;


    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
