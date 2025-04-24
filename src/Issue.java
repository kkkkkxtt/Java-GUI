
public class Issue {
    private String issueId;
    private String hallId;
    private String date;
    private String details;
    private String staff;
    private String status;
    private String respond;

    public Issue(String issueId, String hallId, String date, String details, String staff, String status, String respond) {
        this.issueId = issueId;
        this.hallId = hallId;
        this.date = date;
        this.details = details;
        this.staff = staff;
        this.status = status;
        this.respond = respond;
    }

    public String getIssueId() {
        return issueId;
    }
    public void setIssueId(String name) {
        this.issueId = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHallId() {
        return hallId;
    }
    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStaff() {
        return staff;
    }
    public void setStaff(String staff) {
        this.staff = staff;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRespond() {
        return respond;
    }
    public void setRespond(String respond) {
        this.respond = respond;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", issueId, hallId, date, details, staff, status, respond);
    }
}
