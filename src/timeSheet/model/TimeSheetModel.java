package timeSheet.model;

import java.util.Date;

public class TimeSheetModel {
    private Long r;
    private Long id;
    private Date dateDate;
    private int active;

    public Long getR() {
        return r;
    }

    public void setR(Long r) {
        this.r = r;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDate() {
        return dateDate;
    }

    public void setDateDate(Date dateDate) {
        this.dateDate = dateDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "TimeSheetModel{" +
                "r=" + r +
                ", id=" + id +
                ", dateDate=" + dateDate +
                ", active=" + active +
                '}';
    }
}
