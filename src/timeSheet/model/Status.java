package timeSheet.model;

public class Status extends TimeSheetModel {
    private String name;
    private String description;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Status{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
