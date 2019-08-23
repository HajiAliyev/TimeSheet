package timeSheet.model;

public class Monthes extends TimeSheetModel{
    private String name;
    private String days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Monthes{" +
                "name='" + name + '\'' +
                ", days='" + days + '\'' +
                '}';
    }
}
