package hotelbooking.entities;

public class Room {

    private int id;
    private String number;
    private String type;
    private double pricePerNight;

    public Room() {
    }

    public Room(int id, String number, String type, double pricePerNight) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public String toString() {
        return "hotelbooking.entities.Room{id=" + id + ", number='" + number +
                "', type='" + type + "', pricePerNight=" + pricePerNight + "}";
    }
}
