package aithings.camAI.controlpanel.entity;

import aithings.camAI.controlpanel.utils.constant.DocumentConstants;
import aithings.camAI.controlpanel.utils.constant.FieldEntityConstants.BucketDocumentConstants;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

public class BucketEntity extends BaseEntity {


    @Id
    private String id;
    @Field(BucketDocumentConstants.SIZE)
    private Long size;
    @Field(DocumentConstants.NAME)
    private String name;

    @Field(BucketDocumentConstants.NUMBEROBJECT)
    private Long numberObject;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberObject() {
        return numberObject;
    }

    public void setNumberObject(Long numberObject) {
        this.numberObject = numberObject;
    }
}
