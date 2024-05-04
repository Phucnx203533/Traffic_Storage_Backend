package aithings.camAI.controlpanel.entity;

import aithings.camAI.controlpanel.utils.constant.DocumentConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document(DocumentConstants.RULE_CONFIG)
public class RuleConfigEntity {
    @Id
    private String id;

    private List<TrafficLightEntity> trafficLightEntities = new ArrayList<>();

    private List<LineEntity> lineEntities = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TrafficLightEntity> getTrafficLightEntities() {
        return trafficLightEntities;
    }

    public void setTrafficLightEntities(List<TrafficLightEntity> trafficLightEntities) {
        this.trafficLightEntities = trafficLightEntities;
    }

    public List<LineEntity> getLineEntities() {
        return lineEntities;
    }

    public void setLineEntities(List<LineEntity> lineEntities) {
        this.lineEntities = lineEntities;
    }
}
