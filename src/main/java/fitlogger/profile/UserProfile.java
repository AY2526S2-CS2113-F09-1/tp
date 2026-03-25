package fitlogger.profile;

public class UserProfile {
    private String name;
    private float weight;
    private float height;

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

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
