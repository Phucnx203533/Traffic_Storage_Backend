package aithings.camAI.controlpanel.entity;

public class TrafficLightEntity {

    private String name;
    private Integer id;
    private Integer[] locaiton ={0,0,0,0};


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

    public Integer[] getLocaiton() {
        return locaiton;
    }

    public void setLocaiton(Integer[] locaiton) {
        this.locaiton = locaiton;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
