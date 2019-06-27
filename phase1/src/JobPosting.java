import java.util.Date;

public class JobPosting{

    private Date datePosted;
    private Date dateClosed;
    private String position;

    public Date getDatePosted() { return this.datePosted; }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public JobPosting(Date datePosted, Date dateClosed, String position) {
        this.datePosted = datePosted;
        this.dateClosed = dateClosed;
        this.position = position;
    }
}
