package fitlogger.profile;

public class UserProfile {
    private String name;
    private double weight;
    private double height;

    public UserProfile() {
        name = null;
        weight = -1;
        height = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String toFileFormat() {
        return "name: " + name + " height: " + height + " weight: " + weight;
    }
}
