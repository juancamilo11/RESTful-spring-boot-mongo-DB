package co.com.sofka.dto;

public class EmployeeDTO {

    private String id;
    private String name;
    private String position;

    public EmployeeDTO() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}
