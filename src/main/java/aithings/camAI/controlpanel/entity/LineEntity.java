package aithings.camAI.controlpanel.entity;

public class LineEntity {

    private String name;

    private Integer id;
    private Integer[] location ={0,0,0,0};

    private String[] attributes;

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

    public Integer[] getLocation() {
        return location;
    }

    public void setLocation(Integer[] location) {
        this.location = location;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public void setAttributes(String[] attributes) {
        this.attributes = attributes;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
