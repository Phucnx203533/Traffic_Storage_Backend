package aithings.camAI.controlpanel.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;


@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Field(value = CREATED_AT)
    @CreatedDate
    protected Date createdAt;

    @Field(value = CREATED_BY)
    @CreatedBy
    protected String createdBy;

    @Field(value = MODIFIED_AT)
    @LastModifiedDate
    protected Date modifiedAt;

    @Field(value = MODIFIED_BY)
    @LastModifiedBy
    protected String modifiedBy;

}
