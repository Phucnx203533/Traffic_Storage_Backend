package aithings.camAI.controlpanel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import aithings.camAI.controlpanel.utils.WebUtil;
import aithings.camAI.controlpanel.utils.constant.ActionType;

import javax.servlet.http.HttpServletRequest;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;


@Document(AUDIT_COLLECTION)
@Data
public class SAAuditEntity extends BaseEntity {

    @Id
    private String logId;

    @Field(USER_NAME)
    private String userName;

    @Field(FULL_NAME)
    private String fullName;

    @Field(IP_ADDRESS)
    private String ipAddress;

    @Field(ACTION_TYPE)
    private String actionType;

    @Field(DESCRIPTION)
    private String description;

    @Field(AFFECTED_TABLE)
    private String affectedTable;

    @Field(OLD_VALUE)
    private String oldValue;

    @Field(NEW_VALUE)
    private String newValue;

    public SAAuditEntity(HttpServletRequest request, String description, String actionType,
                         String affectedTable, String oldValue, String newValue) {
        SAUserEntity user = WebUtil.getCurrentUser();
        this.userName = user.getId();
        this.fullName = user.getFullName();
        this.description = description;
        if (actionType.equals(description)) {
            this.description = ActionType.getName(actionType);
        }
        if (request != null) {
            this.ipAddress = WebUtil.getClientIp(request);
        }
        this.actionType = actionType;
        this.affectedTable = affectedTable;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public SAAuditEntity() {
    }
}
