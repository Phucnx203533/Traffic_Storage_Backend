package aithings.camAI.controlpanel.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(SA_ROLE_COLLECTION)
@Getter
@Setter
public class SARoleEntity extends BaseEntity{

    @Id
    @Field(value = ID)
    private String id;

    @Field(NAME)
    private String name;

    @Field(DESCRIPTION)
    private String description;

    @Field(FUNCTIONS)
    private List<String> functions;

    @Field(STATUS)
    private Integer status;

    @Field(PRIORITY)
    private Integer priority;

}
