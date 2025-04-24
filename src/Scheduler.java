
public class Scheduler {
    private String userid;
    private String username;
    private char[] password;

    //constructor
    public Scheduler(String userid,String username, char[] password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    //setters and getters
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }
}
