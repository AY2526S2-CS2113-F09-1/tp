package fitlogger.profile;

public class UserProfile {
    private String name = null;
    private float weight = -1;
    private float height = -1;

    public UserProfile(String name, float weight, float height) {
        assert weight >= 0;
        assert height >= 0;
        assert !name.isBlank();
        this.name = name;
        this.weight = weight;
        this.height = height;
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
