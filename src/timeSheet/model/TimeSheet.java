package timeSheet.model;

public class TimeSheet extends TimeSheetModel {

    private Member member;
    private Monthes monthes;
    private String status;
    private Long day;
    private String description;
    private Integer hour;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Monthes getMonthes() {
        return monthes;
    }

    public void setMonthes(Monthes monthes) {
        this.monthes = monthes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TimeSheet{" +
                "member=" + member +
                ", monthes=" + monthes +
                ", status='" + status + '\'' +
                ", day=" + day +
                ", description='" + description + '\'' +
                ", hour=" + hour +
                '}';
    }
}
