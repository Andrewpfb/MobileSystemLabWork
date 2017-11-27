package example.mvvm.models;

/**
 * Created by frost on 27.11.2017.
 */

public class User {

       /* constructor */

    private String name;
    private String surname;
    private String status;
    private boolean isOnline;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

       /* getters and setters */
}
