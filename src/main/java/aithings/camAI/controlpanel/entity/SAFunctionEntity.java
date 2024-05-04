package aithings.camAI.controlpanel.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import aithings.camAI.controlpanel.dto.SubFunctionDTO;

import java.util.List;

import static aithings.camAI.controlpanel.utils.constant.DocumentConstants.*;

@Document(SA_FUNCTION_COLLECTION)
@Data
public class SAFunctionEntity {

    @Id
    private String id;

    @Field(name = DESCRIPTION)
    private String description;

    @Field(SUB_FUNCTIONS)
    private List<SubFunctionDTO> subFunctions;

    @Field(ORDER)
    private int order;

    @Field(ICON_SVG)
    private String iconSvg;

    @Field(HREF)
    private String href;

}
