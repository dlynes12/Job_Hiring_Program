import java.time.Instant;
import java.util.Date;

public abstract class User {
    private String username;
    private String password;
    private Date dateCreated = new Date();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        Instant now = Instant.now();
        this.dateCreated.from(now);
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Date getDateCreated(){ return this.dateCreated; }

}


