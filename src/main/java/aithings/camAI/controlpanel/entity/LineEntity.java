package aithings.camAI.controlpanel.entity;

public class LineEntity {

    private String name;

    private Integer id;
    private Double[] location;

    private String[] attributes;
    private Integer direct;

    private Integer priority;
    private Long createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double[] getLocation() {
        return location;
    }

    public void setLocation(Double[] location) {
        this.location = location;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attribute) {
        this.attributes= attribute;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
