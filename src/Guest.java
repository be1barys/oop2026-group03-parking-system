import java.util.Objects;

public class Guest {

    private int id;
    private String name;
    private String email;


    public Guest() {
    }

    public Guest(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Guest{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
