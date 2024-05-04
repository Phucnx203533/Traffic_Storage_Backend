package aithings.camAI.controlpanel.entity;

import  lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(collection = USER_COLLECTION)
@Getter
@Setter
public class SAUserEntity extends BaseEntity {
    @Id
    @Field(ID)
    private String id;

//    @Field(EMPLOYEE_CODE)
//    private String employeeCode;

    @Field(EMAIL)
    private String email;

//    @Field(TYPE)
//    private String type;
//    @Field(NO)
//    private String no;
    @Field(PASSWORD)
    private String password;

    @Field(PHONE)
    private String phone;

    @Field(ADDRESS)
    private String address;

    @Field(FULL_NAME)
    private String fullName;

    @Field(DATE_OF_BIRTH)
    private Date dateOfBirth;
    @Field(NO)
    private String no;


    @Field(ROLE)
    private String role;
    @Field(WORKING_UNIT)
    private String workingUnit;

    @Field(STATUS)
    private Integer status;

    @Field(LAST_LOGIN)
    private Date lastLogin;

    @Field(RESET_PASS)
    private Integer resetPass;

    @Field(FAIL_LOGIN_COUNT)
    private int failLoginCount; //đăng nhập sai quá 5 lần => Khóa tài khoản

}
