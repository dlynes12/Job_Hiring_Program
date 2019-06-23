import java.util.Date;

public class JobPosting implements UserAcess{

    private Date datePosted;
    private Date dateClsoed;
    private String position;

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public Date getDateClsoed() {
        return dateClsoed;
    }

    public void setDateClsoed(Date dateClsoed) {
        this.dateClsoed = dateClsoed;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public JobPosting(Date datePosted, Date dateClsoed, String position) {
        this.datePosted = datePosted;
        this.dateClsoed = dateClsoed;
        this.position = position;
    }
}
